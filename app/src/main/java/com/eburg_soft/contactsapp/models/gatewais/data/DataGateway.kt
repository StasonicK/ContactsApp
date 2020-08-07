package com.eburg_soft.contactsapp.models.gateway.data

import com.eburg_soft.contactsapp.models.source.database.entity.Contact
import io.reactivex.Completable
import io.reactivex.Flowable

interface DataGateway {
    fun getAllContacts(): Flowable<List<Contact>>

    fun eraseData(): Completable

    fun syncData(): Completable
}