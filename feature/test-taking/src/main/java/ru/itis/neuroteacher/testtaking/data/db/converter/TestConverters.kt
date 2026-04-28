package ru.itis.neuroteacher.testtaking.data.db.converter

import androidx.room.TypeConverter
import ru.itis.neuroteacher.testtaking.data.db.model.SourceType

class TestConverters {
    @TypeConverter
    fun fromSourceType(sourceType: SourceType): String {
        return sourceType.name
    }

    @TypeConverter
    fun toSourceType(value: String): SourceType {
        return SourceType.valueOf(value)
    }
}