package com.eburg_soft.contactsapp.database

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.runner.*

@RunWith(AndroidJUnit4ClassRunner::class)
class ContactDaoTest {

//    private lateinit var db: ContactsDatabase
//    private lateinit var contactDao: ContactDao
//
//    @Before
//    fun setUp() {
//        db = Room.inMemoryDatabaseBuilder(
//                InstrumentationRegistry.getInstrumentation().context, ContactsDatabase::class.java
//            )
//            .build()
//
//        contactDao = db.contactDao()
//    }
//
//    @Test
//    fun whenInsertContactThenRead() {
//        val contacts =
//            ContactTestHelper.createListOfContacts()
//        contactDao.insert(contacts[0])
//        val completable = contactDao.getAllContacts().test()
//        val dbContacts = contactDao.getAllContacts()
//
//        completable
//            .assertResult(listOf(contacts[0]))
//            .assertNoErrors()
//    }
//
//    @After
//    fun tearDown() {
//        contactDao.deleteContacts()
//        db.close()
//    }
}