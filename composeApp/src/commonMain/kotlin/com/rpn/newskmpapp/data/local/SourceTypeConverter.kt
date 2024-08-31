package com.rpn.newskmpapp.data.local

import androidx.room.TypeConverter
import com.rpn.newskmpapp.data.remote.dto.SourceDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SourceTypeConverter {

    @TypeConverter
    fun fromSourceToString(value: SourceDto): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToSource(value: String): SourceDto {
        return Json.decodeFromString(value)
    }

}