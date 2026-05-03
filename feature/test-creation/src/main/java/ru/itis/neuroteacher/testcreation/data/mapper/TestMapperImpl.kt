package ru.itis.neuroteacher.testcreation.data.mapper

import kotlinx.serialization.json.Json
import ru.itis.neuroteacher.network.model.response.GeneratedTestDto
import ru.itis.neuroteacher.network.model.response.QuestionDto
import ru.itis.neuroteacher.testcreation.data.db.model.SourceType
import ru.itis.neuroteacher.testcreation.data.db.model.TestEntity
import ru.itis.neuroteacher.testcreation.data.db.model.TestResultEntity
import ru.itis.neuroteacher.testcreation.data.model.QuestionDataModel
import ru.itis.neuroteacher.testcreation.data.model.TestDataModel
import ru.itis.neuroteacher.testcreation.domain.model.Question
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.model.TestResult
import javax.inject.Inject

internal class TestMapperImpl @Inject constructor(
    private val json: Json
) : TestMapper {

    override fun toDataModel(dto: GeneratedTestDto): TestDataModel {
        return TestDataModel(
            title = dto.title,
            questions = dto.questions.map { it.toDataModel() }
        )
    }

    private fun QuestionDto.toDataModel(): QuestionDataModel {
        return QuestionDataModel(
            text = this.question,
            options = this.options,
            correctIndex = this.correct,
            explanation = this.explanation
        )
    }

    override fun toDomain(dataModel: TestDataModel): Test {
        return Test(
            title = dataModel.title,
            questions = dataModel.questions.map { it.toDomain() }
        )
    }

    private fun QuestionDataModel.toDomain(): Question {
        return Question(
            text = this.text,
            options = this.options,
            correctIndex = this.correctIndex,
            explanation = this.explanation
        )
    }

    override fun toEntity(test: Test, sourceType: SourceType): TestEntity {
        val questionDataList = test.questions.map { question ->
            QuestionDataModel(
                text = question.text,
                options = question.options,
                correctIndex = question.correctIndex,
                explanation = question.explanation
            )
        }
        val testDataModel = TestDataModel(
            title = test.title,
            questions = questionDataList
        )
        val questionsJson = json.encodeToString(testDataModel)

        return TestEntity(
            title = test.title,
            sourceType = sourceType,
            questionsJson = questionsJson,
            totalQuestions = test.questions.size
        )
    }

    override fun toDomain(entity: TestEntity): Test {
        val testDataModel = json.decodeFromString<TestDataModel>(entity.questionsJson)
        val questions = testDataModel.questions.map { questionData ->
            Question(
                text = questionData.text,
                options = questionData.options,
                correctIndex = questionData.correctIndex,
                explanation = questionData.explanation
            )
        }
        return Test(
            title = testDataModel.title,
            questions = questions
        )
    }

    override fun toResultEntity(
        testId: Long,
        totalQuestions: Int,
        correctAnswers: Int,
        scorePercentage: Float,
        answers: List<Int>
    ): TestResultEntity {
        return TestResultEntity(
            testId = testId,
            totalQuestions = totalQuestions,
            correctAnswers = correctAnswers,
            scorePercentage = scorePercentage,
            answersJson = json.encodeToString(answers)
        )
    }

    override fun toDomainResult(
        testEntity: TestEntity,
        resultEntity: TestResultEntity
    ): TestResult {
        val testDataModel = json.decodeFromString<TestDataModel>(testEntity.questionsJson)
        val selectedIndices = json.decodeFromString<List<Int>>(resultEntity.answersJson)

        val questionResults = testDataModel.questions.mapIndexed { index, q ->
            val selected = selectedIndices.getOrNull(index) ?: 0
            TestResult.QuestionResult(
                questionNumber = index + 1,
                questionText = q.text,
                selectedOptionIndex = selected,
                correctOptionIndex = q.correctIndex,
                isCorrect = selected == q.correctIndex,
                explanation = q.explanation
            )
        }

        return TestResult(
            testTitle = testDataModel.title,
            totalQuestions = resultEntity.totalQuestions,
            correctAnswers = resultEntity.correctAnswers,
            scorePercentage = resultEntity.scorePercentage,
            questions = questionResults
        )
    }
}