package ru.itis.neuroteacher.testcreation.data.mapper

import ru.itis.neuroteacher.network.model.response.GeneratedTestDto
import ru.itis.neuroteacher.network.model.response.QuestionDto
import ru.itis.neuroteacher.testcreation.data.model.QuestionDataModel
import ru.itis.neuroteacher.testcreation.data.model.TestDataModel
import ru.itis.neuroteacher.testcreation.domain.model.Question
import ru.itis.neuroteacher.testcreation.domain.model.Test
import javax.inject.Inject

internal class TestMapper @Inject constructor() {

    fun toDataModel(dto: GeneratedTestDto): TestDataModel {
        return TestDataModel(
            title = dto.title,
            questions = dto.questions.map { toDataModel(it) }
        )
    }

    private fun toDataModel(dto: QuestionDto): QuestionDataModel {
        return QuestionDataModel(
            text = dto.question,
            options = dto.options,
            correctIndex = dto.correct,
            explanation = dto.explanation
        )
    }

    fun toDomain(dataModel: TestDataModel): Test {
        return Test(
            title = dataModel.title,
            questions = dataModel.questions.map { toDomain(it) }
        )
    }

    private fun toDomain(dataModel: QuestionDataModel): Question {
        return Question(
            text = dataModel.text,
            options = dataModel.options,
            correctIndex = dataModel.correctIndex,
            explanation = dataModel.explanation
        )
    }
}
