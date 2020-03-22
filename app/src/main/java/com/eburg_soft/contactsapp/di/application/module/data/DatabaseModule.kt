package com.eburg_soft.contactsapp.di.application.module.data

import android.content.Context
import androidx.room.Room
import com.eburg_soft.contactsapp.di.application.module.app.AppContext
import com.eburg_soft.contactsapp.di.application.scope.AppScope
import com.eburg_soft.contactsapp.model.source.database.ContactsDatabase
import com.eburg_soft.contactsapp.model.source.database.dao.ContactDao
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    private const val DATABASE_NAME = "Contacts.db"

    @Provides
    @AppScope
    fun provideContactDatabase(@AppContext context: Context): ContactsDatabase {
        return Room.databaseBuilder(context, ContactsDatabase::class.java, DATABASE_NAME)
            .build()
    }

    @Provides
    @AppScope
    fun provideContactDao(contactsDatabase: ContactsDatabase): ContactDao {
        return contactsDatabase.contactDao()
    }
}