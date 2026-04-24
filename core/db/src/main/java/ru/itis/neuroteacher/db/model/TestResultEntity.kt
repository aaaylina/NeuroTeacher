package ru.itis.neuroteacher.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.itis.neuroteacher.db.converter.Converters
import java.util.Date

@Entity(tableName = "test_results")
@TypeConverters(Converters::class)
data class TestResultEntity(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    val testId: String,
    val dateCompleted: Date = Date(),
    val totalQuestions: Int,
    val correctAnswers: Int,
    val scorePercentage: Float,
    val answersJson: String
)

