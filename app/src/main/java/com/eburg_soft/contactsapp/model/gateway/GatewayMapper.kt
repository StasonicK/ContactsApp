package com.eburg_soft.contactsapp.model.gateway

import com.eburg_soft.contactsapp.model.ContactRes
import com.eburg_soft.contactsapp.model.source.database.entity.Contact

class GatewayMapper {
    companion object {
        fun mapContact(responses: List<ContactRes>): List<Contact> {
            var contacts = mutableListOf<Contact>()
            for (i in 1 until responses.size) {
                var contact = Contact()
                contact.contactId = responses[i].id
                contact.contactName = responses[i].name
                contact.contactPhone = responses[i].phone
                contact.contactHeight = responses[i].height.toFloat()
                contact.contactBiography = responses[i].biography
                contact.contactTemperament = responses[i].temperament
                contact.contactEducationStart = responses[i].contactEducationPeriodRes.start
                contact.contactEducationEnd = responses[i].contactEducationPeriodRes.end
                contacts.add(contact)
            }
            return contacts
        }
    }
}