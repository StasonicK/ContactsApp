package com.eburg_soft.contactsapp.models.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eburg_soft.contactsapp.models.source.database.daos.ContactDao
import com.eburg_soft.contactsapp.models.source.database.entities.Contact

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false
)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
}