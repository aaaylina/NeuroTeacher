package ru.itis.neuroteacher.testcreation.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json
import ru.itis.neuroteacher.domain.model.RecentTestItem
import ru.itis.neuroteacher.testcreation.data.db.dao.TestDao
import ru.itis.neuroteacher.testcreation.data.db.dao.TestResultDao
import ru.itis.neuroteacher.testcreation.data.db.model.SourceType
import ru.itis.neuroteacher.testcreation.data.db.model.TestResultEntity
import ru.itis.neuroteacher.testcreation.data.mapper.TestMapper
import ru.itis.neuroteacher.testcreation.data.model.TestDataModel
import ru.itis.neuroteacher.testcreation.data.model.remote.QuestionDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizDto
import ru.itis.neuroteacher.testcreation.domain.model.Question
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.model.TestResult
import ru.itis.neuroteacher.testcreation.domain.model.TestStatistics
import ru.itis.neuroteacher.testcreation.domain.repository.FirebaseQuizRepository
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

internal class TestRepositoryImpl @Inject constructor(
    private val dao: TestDao,
    private val testResultDao: TestResultDao,
    private val mapper: TestMapper,
    private val json: Json,
    private val firebaseRepo: FirebaseQuizRepository
) : TestRepository {

    private val remoteQuizMutex = Mutex()

    override suspend fun saveTest(test: Test, sourceType: SourceType): Long {
        val entity = mapper.toEntity(test, sourceType)
        val id = dao.insertTest(entity)
        return id
    }

    override suspend fun getOrCreateRemoteQuizId(localTestId: Long, test: Test): Result<String> = runCatching {
        remoteQuizMutex.withLock {
            val entity = dao.getTestById(localTestId)
                ?: throw IllegalStateException("Локальный тест $localTestId не найден")
            val existing = entity.firestoreId?.takeIf { it.isNotBlank() }
            if (existing != null) {
                return@runCatching existing
            }
            val quizDto = test.toQuizDto()
            val quizId = firebaseRepo.saveQuiz(quizDto).getOrThrow()
            dao.updateTest(entity.copy(firestoreId = quizId))
            quizId
        }
    }

    override suspend fun getTestById(id: Long): Test? {
        val entity = dao.getTestById(id) ?: return null
        return mapper.toDomain(entity)
    }

    override suspend fun getAllTests(): List<Test> {
        return dao.getAllTestsSortedByDateDesc().map { mapper.toDomain(it) }
    }

    override suspend fun saveResult(
        testId: Long,
        totalQuestions: Int,
        correctAnswers: Int,
        scorePercentage: Float,
        answers: List<Int>
    ): Long {
        val entity = mapper.toResultEntity(
            testId = testId,
            totalQuestions = totalQuestions,
            correctAnswers = correctAnswers,
            scorePercentage = scorePercentage,
            answers = answers
        )
        val id = testResultDao.insertResult(entity)
        return id
    }

    override suspend fun getResultById(resultId: Long): TestResult? {
        val resultEntity = testResultDao.getResultById(resultId) ?: return null
        val testEntity = dao.getTestById(resultEntity.testId) ?: return null
        return mapper.toDomainResult(testEntity, resultEntity)
    }

    override suspend fun getResultsByTestId(testId: Long): List<TestResult> {
        val testEntity = dao.getTestById(testId) ?: return emptyList()
        val resultEntities = testResultDao.getResultsByTestIdSortedByDateDesc(testId)
        return resultEntities.map { mapper.toDomainResult(testEntity, it) }
    }

    override suspend fun getTotalTestsCount(): Int = dao.getTotalTestsCount()

    override suspend fun getTotalCompletedTestsCount(): Int = testResultDao.getTotalCompletedTestsCount()

    override suspend fun getAverageScore(): Float? = testResultDao.getAverageScore()

    override suspend fun getBestScore(): Float? = testResultDao.getBestScore()

    override suspend fun getTestStatistics(): TestStatistics {
        val remoteStats = getRemoteTestStatistics().getOrNull()
        val localStats = TestStatistics(
            totalTests = getTotalTestsCount(),
            completedTests = getTotalCompletedTestsCount(),
            averageScore = getAverageScore() ?: 0f,
            bestScore = getBestScore() ?: 0f
        )

        return if (remoteStats != null && remoteStats.totalTests > 0) {
            remoteStats
        } else {
            localStats
        }
    }

    override suspend fun clearAllData() {
        dao.deleteAllTests()
        testResultDao.deleteAllResults()
    }

    override suspend fun getAllTestResults(): List<TestResult> {
        val results = testResultDao.getAllResultsFlowSortedByDateDesc().first()
        return results.mapNotNull { resultEntity ->
            val testEntity = dao.getTestById(resultEntity.testId) ?: return@mapNotNull null
            mapper.toDomainResult(testEntity, resultEntity)
        }
    }

    override fun getTestResultsFlow(query: String): Flow<List<TestResult>> {
        return if (query.isBlank()) {
            testResultDao.getAllResultsFlowSortedByDateDesc()
        } else {
            testResultDao.searchResultsByTestTitle(query)
        }.map { entities ->
            entities.mapNotNull { resultEntity ->
                val testEntity = dao.getTestById(resultEntity.testId)
                if (testEntity != null) {
                    mapper.toDomainResult(testEntity, resultEntity)
                } else {
                    null
                }
            }
        }
    }

    override suspend fun getAllTestsForHome(): List<RecentTestItem> {
        val localResults = testResultDao.getAllResultsFlowSortedByDateDesc().first()

        val items = mutableListOf<RecentTestItem>()

        items.addAll(localResults.mapNotNull { resultEntity ->
            val testEntity = dao.getTestById(resultEntity.testId) ?: return@mapNotNull null
            val testData = json.decodeFromString<TestDataModel>(testEntity.questionsJson)

            RecentTestItem(
                title = testData.title,
                date = formatDate(resultEntity.dateCompleted),
                scorePercentage = resultEntity.scorePercentage.toInt()
            )
        })

        val remoteQuizzes = firebaseRepo.getUserQuizzes().getOrNull() ?: emptyList()
        val takenQuizTitles = items.map { it.title }.toSet()

        remoteQuizzes.forEach { quiz ->
            if (!takenQuizTitles.contains(quiz.title)) {
                items.add(
                    RecentTestItem(
                        title = quiz.title,
                        date = formatDate(quiz.createdAt),
                        scorePercentage = 0
                    )
                )
            }
        }

        return items.sortedByDescending { it.date }
    }

    override suspend fun loadRemoteTests(): Result<Unit> = runCatching {

        val quizzes = firebaseRepo.getUserQuizzes().getOrThrow()

        quizzes.forEach { quizDto ->
            try {
                val localRows = dao.getAllTestsSortedByDateDesc()
                val existsByFirestoreId =
                    quizDto.id.isNotBlank() && localRows.any { it.firestoreId == quizDto.id }
                val existsByTitle = localRows.any { it.title == quizDto.title }

                if (quizDto.id.isNotBlank()) {
                    val orphan = localRows.find {
                        it.title == quizDto.title && (it.firestoreId == null || it.firestoreId.isBlank())
                    }
                    if (orphan != null && orphan.firestoreId != quizDto.id) {
                        dao.updateTest(orphan.copy(firestoreId = quizDto.id))
                    }
                }

                if (!existsByFirestoreId && !existsByTitle) {
                    val test = convertQuizDtoToTest(quizDto)
                    val localId = saveTest(test, SourceType.TEXT)
                    if (quizDto.id.isNotBlank()) {
                        dao.getTestById(localId)?.let { entity ->
                            dao.updateTest(entity.copy(firestoreId = quizDto.id))
                        }
                    }
                }
            } catch (e: Exception) {
            }
        }

    }

    override suspend fun loadRemoteResults(): Result<Unit> = runCatching {
        val results = firebaseRepo.getQuizResults().getOrThrow()

        val localTests = dao.getAllTestsSortedByDateDesc()

        results.forEach { remoteResult ->
            val testEntity = localTests.find { it.firestoreId == remoteResult.quizId }
            if (testEntity == null) {
                return@forEach
            }

            val existingResults = testResultDao.getAllResultsFlowSortedByDateDesc().first()
            val exists = existingResults.any {
                it.testId == testEntity.id && it.dateCompleted == remoteResult.completedAt
            }

            if (!exists) {
                val answersJson = json.encodeToString(remoteResult.answers.map { it.selectedAnswer })
                val resultEntity = TestResultEntity(
                    testId = testEntity.id,
                    dateCompleted = remoteResult.completedAt,
                    totalQuestions = remoteResult.totalQuestions,
                    correctAnswers = remoteResult.correctCount,
                    scorePercentage = remoteResult.score.toFloat(),
                    answersJson = answersJson
                )
                testResultDao.insertResult(resultEntity)
            }
        }
    }

    override suspend fun getRemoteTestStatistics(): Result<TestStatistics> = runCatching {
        val userStats = firebaseRepo.getUserStatistics().getOrThrow()

        TestStatistics(
            totalTests = userStats?.totalQuizzes ?: 0,
            completedTests = userStats?.totalCompleted ?: 0,
            averageScore = userStats?.averageScore ?: 0f,
            bestScore = userStats?.bestScore?.toFloat() ?: 0f
        )
    }

    override fun observeRemoteStatistics(): Flow<TestStatistics> {
        return firebaseRepo.observeUserStatistics().map { userStats ->
            TestStatistics(
                totalTests = userStats?.totalQuizzes ?: 0,
                completedTests = userStats?.totalCompleted ?: 0,
                averageScore = userStats?.averageScore ?: 0f,
                bestScore = userStats?.bestScore?.toFloat() ?: 0f
            )
        }
    }

    private fun Test.toQuizDto(): QuizDto {
        val questionDtos = questions.map { q ->
            QuestionDto(
                text = q.text,
                answers = q.options,
                correctAnswer = q.correctIndex,
                explanation = q.explanation
            )
        }
        return QuizDto(title = title, questions = questionDtos)
    }

    private fun convertQuizDtoToTest(quizDto: QuizDto): Test {
        val questions = quizDto.questions.map { questionDto ->
            Question(
                text = questionDto.text,
                options = questionDto.answers,
                correctIndex = questionDto.correctAnswer,
                explanation = questionDto.explanation
            )
        }
        return Test(
            title = quizDto.title,
            questions = questions
        )
    }

    private fun formatDate(date: Date): String {
        return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
    }
}