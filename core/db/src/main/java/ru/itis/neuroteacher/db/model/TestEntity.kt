package ru.itis.neuroteacher.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.itis.neuroteacher.db.converter.Converters
import java.util.Date

@Entity(tableName = "tests")
@TypeConverters(Converters::class)
data class TestEntity(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val dateCreated: Date = Date(),
    val sourceType: SourceType,
    val questionsJson: String,
    val totalQuestions: Int,
    val resultId: String? = null
)

enum class SourceType {
    CAMERA, TEXT
}
