package com.eburg_soft.contactsapp.di.application.module.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eburg_soft.contactsapp.model.source.database.dao.ContactDao
import com.eburg_soft.contactsapp.model.source.database.entity.Contact

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false
)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
}