package ru.itis.neuroteacher.testcreation.utils.prompt

import javax.inject.Inject

internal class TestPromptBuilder @Inject constructor() {

    fun buildSystemPrompt(): String {
        return """
            Ты — профессиональный эксперт по созданию тестов. 
            Твоя задача: генерировать учебные материалы строго в формате JSON.
            
            ПРАВИЛА:
            - Отвечай ТОЛЬКО валидным JSON-объектом.[cite: 14, 15]
            - Запрещено использовать Markdown разметку (никаких ```json).[cite: 15]
            - Запрещен любой текст, комментарии или пояснения до и после JSON.[cite: 15]
        """.trimIndent()
    }

    fun buildGenerationPrompt(text: String, questionCount: Int): String {
        return """
            Создай тест из $questionCount вопросов на основе следующего текста:
            
            $text
            
            СТРОГАЯ СХЕМА JSON (соблюдай ключи СТРОГО):
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
            
            ТЕХНИЧЕСКИЕ ТРЕБОВАНИЯ:
            1. "question": строка с текстом вопроса.[cite: 28]
            2. "options": массив ровно из 4-х строк.[cite: 28]
            3. "correct": целое число (0, 1, 2 или 3) — индекс верного ответа в массиве options.[cite: 28]
            4. "explanation": строка с объяснением (может быть null, если объяснение невозможно составить).[cite: 28]
            5. Язык текста: используй тот же язык, на котором написан исходный текст.
        """.trimIndent()
    }
}