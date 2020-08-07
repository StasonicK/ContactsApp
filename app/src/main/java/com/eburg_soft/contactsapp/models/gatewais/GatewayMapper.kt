package com.eburg_soft.contactsapp.models.gateway

import com.eburg_soft.contactsapp.models.ContactRes
import com.eburg_soft.contactsapp.models.source.database.entity.Contact
import com.eburg_soft.contactsapp.models.source.database.entity.Temperament
import com.eburg_soft.contactsapp.models.source.database.entity.Temperament.MISTAKE
import java.text.SimpleDateFormat

class GatewayMapper {
    companion object {
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

        private fun mapDate(date: String): String {
            val simpleDate = SimpleDateFormat(recieveDatePattern).parse(date)
            return SimpleDateFormat(resultDatePattern).format(simpleDate)
        }

        private fun mapTemperament(temperament: String) =
            Temperament.values().find { it.type == temperament } ?: MISTAKE
    }
}