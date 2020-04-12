package com.eburg_soft.contactsapp.model.gateway

import com.eburg_soft.contactsapp.model.ContactRes
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.model.source.database.entity.Temperament
import com.eburg_soft.contactsapp.model.source.database.entity.Temperament.MISTAKE
import java.text.SimpleDateFormat

class GatewayMapper {
    companion object {
        const val recieveDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ"
        const val resultDatePattern = "dd.MM.yyyy"

        fun mapContact(responses: List<ContactRes>): List<Contact> {
            val contacts = mutableListOf<Contact>()
            for (i in responses.indices) {
                val contact = Contact()
                contact.contactId = responses[i].id
                contact.contactName = responses[i].name
                contact.contactPhone = responses[i].phone
                contact.contactHeight = responses[i].height.toFloat()
                contact.contactBiography = responses[i].biography
                contact.contactTemperament = mapTemperament(responses[i].temperament)
                contact.contactEducationStart = mapDate(responses[i].contactEducationPeriodRes.start)
                contact.contactEducationEnd = mapDate(responses[i].contactEducationPeriodRes.end)
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