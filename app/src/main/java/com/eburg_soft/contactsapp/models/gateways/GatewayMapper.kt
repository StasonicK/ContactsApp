package com.eburg_soft.contactsapp.models.gateways

import com.eburg_soft.contactsapp.models.ContactRes
import com.eburg_soft.contactsapp.models.source.database.entities.Contact
import com.eburg_soft.contactsapp.models.source.database.entities.Temperament
import com.eburg_soft.contactsapp.models.source.database.entities.Temperament.MISTAKE
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GatewayMapper {
    companion object {

        val EXCEPTION_DATE = "Unparseable date"

        private const val recieveDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ"
        private const val resultDatePattern = "dd.MM.yyyy"

        fun mapContact(responses: List<ContactRes>): List<Contact> {
            val contacts = mutableListOf<Contact>()
            responses.forEach {
                val contact = Contact()
                contact.contactId = it.id
                contact.contactName = it.name
                contact.contactPhone = it.phone
                contact.contactHeight = it.height.toFloat()
                contact.contactBiography = it.biography
                contact.contactTemperament = mapTemperament(it.temperament)
                contact.contactEducationStart = mapDate(it.contactEducationPeriodRes.start)
                contact.contactEducationEnd = mapDate(it.contactEducationPeriodRes.end)
                contacts.add(contact)
            }
            return contacts
        }

        @Throws(Exception::class)
        private fun mapDate(date: String): String {
            var simpleDate: Date? = null
            try {
                simpleDate = SimpleDateFormat(recieveDatePattern).parse(date)
            } catch (e: Exception) {
                throw Exception(EXCEPTION_DATE)
            }
            return SimpleDateFormat(resultDatePattern).format(simpleDate)
        }

        private fun mapTemperament(temperament: String): Temperament {
            return Temperament.values().find { it.type == temperament.toLowerCase(Locale.getDefault()) } ?: MISTAKE
        }
    }
}