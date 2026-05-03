package ru.itis.neuroteacher.testcreation.data.mapper

import ru.itis.neuroteacher.network.model.response.GeneratedTestDto
import ru.itis.neuroteacher.testcreation.data.db.model.SourceType
import ru.itis.neuroteacher.testcreation.data.db.model.TestEntity
import ru.itis.neuroteacher.testcreation.data.db.model.TestResultEntity
import ru.itis.neuroteacher.testcreation.data.model.TestDataModel
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.model.TestResult

internal interface TestMapper {
    fun toDataModel(dto: GeneratedTestDto): TestDataModel
    fun toDomain(dataModel: TestDataModel): Test
    fun toEntity(test: Test, sourceType: SourceType): TestEntity
    fun toDomain(entity: TestEntity): Test
    fun toResultEntity(testId: Long, totalQuestions: Int, correctAnswers: Int, scorePercentage: Float, answers: List<Int>): TestResultEntity
    fun toDomainResult(testEntity: TestEntity, resultEntity: TestResultEntity): TestResult
}
