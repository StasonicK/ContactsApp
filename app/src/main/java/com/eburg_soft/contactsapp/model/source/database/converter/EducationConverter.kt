package com.eburg_soft.contactsapp.model.source.database.converter

import androidx.room.TypeConverter
import com.eburg_soft.contactsapp.model.source.database.entity.Education

class EducationConverter {

    @TypeConverter
    fun fromEducationStart(value: Education): String = value.getStart()

    @TypeConverter
    fun fromEducationEnd(value: Education): String = value.getEnd()

    @TypeConverter
    fun toEducationStart(start: String): Education {
        Education.setStart(start)
        return Education
    }

    @TypeConverter
    fun toEducationEnd(end: String): Education {
        Education.setEnd(end)
        return Education
    }
}