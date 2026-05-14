package ru.itis.neuroteacher.testcreation.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.itis.neuroteacher.testcreation.data.db.converter.TestConverters
import java.util.Date

@Entity(tableName = "tests")
@TypeConverters(TestConverters::class)
data class TestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val dateCreated: Date = Date(),
    val sourceType: SourceType,
    val questionsJson: String,
    val totalQuestions: Int,
    val resultId: Long? = null,
    val firestoreId: String? = null,
    val isSynced: Boolean = false
)

enum class SourceType {
    CAMERA, TEXT
}
