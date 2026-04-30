package ru.itis.neuroteacher.testcreation.utils.constants

object TestGenerationConstants {
    const val DEFAULT_MODEL = "openrouter/free"

    const val DEFAULT_TEMPERATURE = 0.7
    const val DEFAULT_MAX_TOKENS = 8000
    const val RESPONSE_FORMAT_TYPE = "json_object"

    const val ROLE_SYSTEM = "system"
    const val ROLE_USER = "user"

    const val MIN_TEXT_LENGTH = 50
    const val MAX_TEXT_LENGTH = 5000
    val QUESTION_COUNT_OPTIONS = listOf(5, 10, 15, 20)
}