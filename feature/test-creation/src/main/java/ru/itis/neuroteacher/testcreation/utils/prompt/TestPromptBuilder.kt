package ru.itis.neuroteacher.testcreation.utils.prompt

import javax.inject.Inject

internal class TestPromptBuilder @Inject constructor() {

    fun buildSystemPrompt(): String {
        return "Ты - эксперт по созданию тестов. Отвечай ТОЛЬКО в формате JSON."
    }

    fun buildGenerationPrompt(text: String, questionCount: Int): String {
        return """
            Создай тест из $questionCount вопросов на основе следующего текста:
            
            $text
            
            Формат ответа должен быть строго JSON:
            {
                "title": "Название теста",
                "questions": [
                    {
                        "question": "Текст вопроса",
                        "options": ["Вариант 1", "Вариант 2", "Вариант 3", "Вариант 4"],
                        "correct": 0,
                        "explanation": "Объяснение правильного ответа"
                    }
                ]
            }
            
            Где correct - индекс правильного ответа (0-based).
            Верни ТОЛЬКО JSON, без дополнительного текста.
        """.trimIndent()
    }
}