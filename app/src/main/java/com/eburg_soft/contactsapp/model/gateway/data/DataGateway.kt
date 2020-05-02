package com.eburg_soft.contactsapp.model.gateway.data

import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import io.reactivex.Completable
import io.reactivex.Flowable

interface DataGateway {
    fun getAllContacts(): Flowable<List<Contact>>

    fun eraseData(): Completable

    fun syncData(): Completable
}