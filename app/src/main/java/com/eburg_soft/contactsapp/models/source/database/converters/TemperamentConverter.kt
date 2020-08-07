package com.eburg_soft.contactsapp.models.source.database.converters

import androidx.room.TypeConverter
import com.eburg_soft.contactsapp.models.source.database.entities.Temperament
import com.eburg_soft.contactsapp.models.source.database.entities.Temperament.MISTAKE

class TemperamentConverter {

    @TypeConverter
    fun fromTemperament(temperament: Temperament): String {
        return temperament.type
    }

    @TypeConverter
    fun toTemperament(temperament: String) = Temperament.values().find { it.type == temperament } ?: MISTAKE
}