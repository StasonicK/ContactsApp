package com.eburg_soft.contactsapp.models.source.database.converter

import androidx.room.TypeConverter
import com.eburg_soft.contactsapp.models.source.database.entity.Temperament
import com.eburg_soft.contactsapp.models.source.database.entity.Temperament.MISTAKE

class TemperamentConverter {

    @TypeConverter
    fun fromTemperament(temperament: Temperament): String {
        return temperament.type
    }

    @TypeConverter
    fun toTemperament(temperament: String) = Temperament.values().find { it.type == temperament } ?: MISTAKE
}