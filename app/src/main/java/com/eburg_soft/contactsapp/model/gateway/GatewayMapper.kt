package com.eburg_soft.contactsapp.model.gateway

import com.eburg_soft.contactsapp.model.ContactRes
import com.eburg_soft.contactsapp.model.source.database.entity.Contact

class GatewatMapper {
    companion object {
        fun mapContact(responses: List<ContactRes>): List<Contact> {
            var contacts: List<Contact> = mutableListOf()
            for (i in 1..responses.size) {
                var contact: Contact = Contact()
                contact.contactId = responses[i].id.toLong()
                contact.contactName = responses[i].name
                contact.contactPhone = responses[i].phone
                contact.contactHeingt = responses[i].height.toString()
                contact.contactBiography = responses[i].biography
                contact.contactTemperament = responses[i].temperament
                contact.contactEducationStart = responses[i].contactEducationPeriodRes.start
                contact.contactEducationEnd = responses[i].contactEducationPeriodRes.end
                contacts.add(contact)
            }
        }
    }
}