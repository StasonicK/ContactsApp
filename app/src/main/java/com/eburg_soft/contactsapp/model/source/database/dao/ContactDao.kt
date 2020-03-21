package com.eburg_soft.contactsapp.model.source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contact: Contact): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contacts: List<Contact>): Completable

    @Update
    fun update(contact: Contact): Completable

    @Update
    fun update(contact: List<Contact>): Completable

    @Query("SELECT * FROM ${Contact.TABLE_NAME}")
    fun getAllContacts(): Single<List<Contact>>

    @Query("SELECT * FROM ${Contact.TABLE_NAME} WHERE id=:id")
    fun getContactById(id: Long): Single<Contact>

    @Query("DELETE FROM " + Contact.TABLE_NAME)
    fun deleteContacts(): Completable
}