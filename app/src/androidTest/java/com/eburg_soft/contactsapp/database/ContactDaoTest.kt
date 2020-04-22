package com.eburg_soft.contactsapp

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.eburg_soft.contactsapp.model.source.database.ContactsDatabase
import com.eburg_soft.contactsapp.model.source.database.dao.ContactDao
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.*

@RunWith(AndroidJUnit4ClassRunner::class)
class ContactDaoTest {

    private lateinit var db: ContactsDatabase
    private lateinit var contactDao: ContactDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context, ContactsDatabase::class.java
            )
            .build()

        contactDao = db.contactDao()
    }

    @Test
    fun whenInsertContactThenRead() {
        val contacts = ContactTestHelper.createListOfContacts()
        contactDao.insert(contacts[0])
//        val testSubscriber = TestSubscriber<Contact>()
        val completable = contactDao.getAllContacts().test()
        val dbContacts = contactDao.getAllContacts()
//        completable.subscribe(testSubscriber)

        completable.assertComplete()
        completable.assertNoErrors()
        completable.assertValueCount(1)

        assertEquals(1, completable.events)
    }

    @After
    fun tearDown() {
        contactDao.deleteContacts()
        db.close()
    }
}