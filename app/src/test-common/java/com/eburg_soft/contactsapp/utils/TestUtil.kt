package com.eburg_soft.contactsapp.utils

import com.eburg_soft.contactsapp.models.ContactEducationPeriodRes
import com.eburg_soft.contactsapp.models.ContactRes
import com.eburg_soft.contactsapp.models.source.database.entities.Contact
import com.eburg_soft.contactsapp.models.source.database.entities.Temperament.CHOLERIC
import com.eburg_soft.contactsapp.models.source.database.entities.Temperament.MELANCHOLIC
import com.eburg_soft.contactsapp.models.source.database.entities.Temperament.PHLEGMATIC
import com.eburg_soft.contactsapp.models.source.database.entities.Temperament.SANGUINE
import java.util.Collections

object TestUtil {

    val CONTACT_ONE = Contact(
        "0", "One", "12345", 178f, "biography1", CHOLERIC,
        "01.09.2004", "30.05.2008"
    )
    val CONTACT_TWO = Contact(
        "1", "Two", "23456", 175f, "biography2", MELANCHOLIC,
        "01.09.2005", "30.05.2009"
    )
    val CONTACT_RES_ONE = ContactRes(
        "0",
        "One",
        "12345",
        178.0,
        "biography1",
        "CHOLERIC",
        ContactEducationPeriodRes("2004-09-01T00:00:00-0200", "2008-05-30T00:00:00-0200")
    )
    val CONTACT_RES_TWO = ContactRes(
        "1",
        "Two",
        "23456",
        175.0,
        "biography2",
        "MELANCHOLIC",
        ContactEducationPeriodRes("2005-09-01T00:00:00-0400", "2009-05-30T00:00:00-0400")
    )
    val CONTACT_RES_WRONG_DATE = ContactRes(
        "1", "Two", "23456", 175.0, "biography2", "MELANCHOLIC", ContactEducationPeriodRes("2004-09-01", "2008-05-30")
    )

    val CONTACTS: List<Contact> = Collections.unmodifiableList(
        object : ArrayList<Contact>() {
            init {
                add(Contact("2", "One", "34567", 170f, "biography3", PHLEGMATIC, "01.09.2006", "30.05.2010"))
                add(Contact("3", "Two", "45678", 181f, "biography4", SANGUINE, "01.09.2007", "30.05.2011"))
            }
        }
    )

    val CONTACTS_RES: List<ContactRes> = Collections.unmodifiableList(
        object : ArrayList<ContactRes>() {
            init {
                add(
                    ContactRes(
                        "0",
                        "One",
                        "12345",
                        178.0,
                        "biography1",
                        "CHOLERIC",
                        ContactEducationPeriodRes("2004-09-01T00:00:00-0600", "2008-05-30T00:00:00-0600")
                    )
                )
                add(
                    ContactRes(
                        "1",
                        "Two",
                        "23456",
                        175.0,
                        "biography2",
                        "MELANCHOLIC",
                        ContactEducationPeriodRes("2005-09-01T00:00:00-0600", "2009-05-30T00:00:00-0600")
                    )
                )
            }
        }
    )
}