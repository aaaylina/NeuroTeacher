package ru.itis.neuroteacher.testcreation.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.itis.neuroteacher.testcreation.data.db.converter.TestConverters
import java.util.Date

@Entity(tableName = "test_results")
@TypeConverters(TestConverters::class)
data class TestResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val testId: Long,
    val dateCompleted: Date = Date(),
    val totalQuestions: Int,
    val correctAnswers: Int,
    val scorePercentage: Float,
    val answersJson: String
)