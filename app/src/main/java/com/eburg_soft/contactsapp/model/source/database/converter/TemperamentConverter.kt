package com.eburg_soft.contactsapp.model.source.database

import androidx.room.TypeConverter
import com.eburg_soft.contactsapp.model.source.database.entity.Temperament
import java.util.Locale

class TemperamentConverter {

    @TypeConverter
    fun fromTemperament(value: Temperament): String {
        return value.type
    }

    @TypeConverter
    fun toTemperament(value: String): Temperament {
        return when (value.toLowerCase(Locale.getDefault())) {
            "melancholic" -> Temperament.MELANCHOLIC
            "phlegmatic" -> Temperament.PHLEGMATIC
            "sanguine" -> Temperament.SANGUINE
            "choleric" -> Temperament.CHOLERIC
            else -> Temperament.MISTAKE
        }
    }
}