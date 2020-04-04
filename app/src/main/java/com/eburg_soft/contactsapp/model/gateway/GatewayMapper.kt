package com.eburg_soft.contactsapp.model.gateway

import com.eburg_soft.contactsapp.model.ContactRes
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.model.source.database.entity.Temperament
import java.util.Locale

class GatewayMapper {
    companion object {
        fun mapContact(responses: List<ContactRes>): List<Contact> {
            val contacts = mutableListOf<Contact>()
            for (i in responses.indices) {
                val contact = Contact()
                contact.contactId = responses[i].id
                contact.contactName = responses[i].name
                contact.contactPhone = responses[i].phone
                contact.contactHeight = responses[i].height.toFloat()
                contact.contactBiography = responses[i].biography
//                contact.contactTemperament = mapTemperament(responses[i].temperament)
                contact.contactTemperament = responses[i].temperament
                contact.contactEducationStart = mapDate(responses[i].contactEducationPeriodRes.start)
                contact.contactEducationEnd = mapDate(responses[i].contactEducationPeriodRes.end)
                contacts.add(contact)
            }
            return contacts
        }

        private fun mapDate(date: String): String {
            val tempDate: String = date.substring(0, date.indexOf("T"))
            val dateArray = tempDate.split("-")
            val dateArrayReversed = dateArray.asReversed()
            val finalDate: StringBuilder = StringBuilder("")
            for (i in dateArrayReversed.indices) {
                if (i == dateArrayReversed.size - 1) {
                    finalDate.append(dateArrayReversed[i])
                } else {
                    finalDate.append(dateArrayReversed[i] + ".")
                }
            }
            return finalDate.toString()
        }

        private fun mapTemperament(value: String): Temperament {
            return when (value.toLowerCase(Locale.getDefault())) {
                "melancholic" -> Temperament.MELANCHOLIC
                "phlegmatic" -> Temperament.PHLEGMATIC
                "sanguine" -> Temperament.SANGUINE
                "choleric" -> Temperament.CHOLERIC
                else -> Temperament.MISTAKE
            }
        }
    }
}