package com.eburg_soft.contactsapp.model.gateway

import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface DataGateway {
    fun getAllContacts(): Single<List<Contact>>

    fun eraseData(): Completable

    fun getContactById(id: String): Single<Contact>

    fun getContactsByPhone(phone: String): Maybe<List<Contact>>

    fun getContactsByName(name: String): Maybe<List<Contact>>

    fun syncData(): Completable
}