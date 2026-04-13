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

    fun toDomain(dataModel: TestDataModel): Test {
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
}
