package com.eburg_soft.contactsapp.database

import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.model.source.database.entity.Temperament.CHOLERIC
import com.eburg_soft.contactsapp.model.source.database.entity.Temperament.PHLEGMATIC
import com.eburg_soft.contactsapp.model.source.database.entity.Temperament.SANGUINE

class ContactTestHelper {
    companion object {
        fun createListOfContacts(): List<Contact> {
            val contacts = ArrayList<Contact>()
            var contact = Contact(
                "5bbb009d5d052e0b9258c316", "Summer Greer", "+7 (903) 425-3032", 201.9f,
                "Non culpa occaecat occaecat sit occaecat aliquip esse Lorem voluptate commodo veniam ipsum velit. Mollit sunt quis reprehenderit pariatur Lorem consequat magna. Nulla nostrud ad deserunt tempor proident enim exercitation sit ullamco aliquip.",
                SANGUINE,
                "2013-07-15T11:44:06-06:00", "2007-08-09T08:26:05-06:00"
            )
            contacts.add(contact)
            contact = Contact(
                "5bbb009ddab326ca572d6efc", "Letha Ramirez", "+7 (858) 476-2312", 161.0f,
                "Ullamco esse quis fugiat do voluptate mollit sit proident aute sunt id. Proident ad enim laborum ullamco ea Lorem dolore labore duis sint deserunt qui est ut. Sint est velit irure occaecat nostrud tempor sit tempor ipsum magna sint esse quis officia.",
                CHOLERIC,
                "2012-03-16T03:20:39-06:00", "2015-09-30T10:40:55-05:00"
            )
            contacts.add(contact)
            contact = Contact(
                "5bbb009d0a125490a5685f41", "Margery Jacobson", "+7 (822) 466-2280", 186.59f,
                "Aliqua aliqua magna sit velit enim mollit voluptate mollit magna. Aliquip laborum labore eu eu anim ipsum laboris dolore laborum sunt. Elit elit cillum culpa sit ullamco.",
                PHLEGMATIC,
                "2016-04-07T12:48:49-05:00", "2016-06-30T08:19:35-05:00"
            )
            contacts.add(contact)
            return contacts
        }
    }

    fun contactsAreIdentical(contactOne: Contact, contactTwo: Contact): Boolean {
        return (contactOne == contactTwo && contactsAreIdenticalByFields(contactOne, contactTwo))
    }

    private fun contactsAreIdenticalByFields(contactOne: Contact, contactTwo: Contact): Boolean {
        return (contactOne.contactId == contactTwo.contactId &&
                contactOne.contactName == contactTwo.contactName &&
                contactOne.contactPhone == contactTwo.contactPhone &&
                contactOne.contactBiography == contactTwo.contactBiography &&
                contactOne.contactHeight == contactTwo.contactHeight &&
                contactOne.contactEducationStart == contactTwo.contactEducationStart &&
                contactOne.contactEducationEnd == contactTwo.contactEducationEnd &&
                contactOne.contactTemperament == contactTwo.contactTemperament
                )
    }
}