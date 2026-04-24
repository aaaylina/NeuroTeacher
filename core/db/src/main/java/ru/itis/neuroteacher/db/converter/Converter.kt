package ru.itis.neuroteacher.db.converter

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromSourceType(sourceType: SourceType): String {
        return sourceType.name
    }

    @TypeConverter
    fun toSourceType(value: String): SourceType {
        return SourceType.valueOf(value)
    }
}