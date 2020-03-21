package com.eburg_soft.contactsapp.model.gateway

import com.eburg_soft.contactsapp.model.ApiClient
import com.eburg_soft.contactsapp.model.source.database.dao.ContactDao
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataGatewayImpl @Inject constructor(
    private val contactDao: ContactDao,
    private val apiClient: ApiClient
) : DataGateway {


    override fun getAllContacts(): Single<List<Contact>> {

        return contactDao.getAllContacts()
            .subscribeOn(Schedulers.io())
    }

    override fun eraseData(): Completable {
        return contactDao.deleteContacts()
    }

    override fun getContactById(id: String): Single<Contact> {
        return contactDao.getContactById(id)
            .subscribeOn(Schedulers.io())
    }

    override fun getContactsByPhone(phone: String): Maybe<List<Contact>> {
        return contactDao.getContactsByPhone(phone)
            .subscribeOn(Schedulers.io())
    }

    override fun getContactsByName(name: String): Maybe<List<Contact>> {
        return contactDao.getContactsByName(name)
            .subscribeOn(Schedulers.io())
    }

    override fun syncData(): Completable {
        return apiClient.getContacts1()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { GatewayMapper.mapContact(it) }
            .flatMapCompletable { t: List<Contact> -> contactDao.insert(t) }
            .andThen(apiClient.getContacts2()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { GatewayMapper.mapContact(it) }
                .flatMapCompletable { t: List<Contact> -> contactDao.insert(t) })
            .andThen(apiClient.getContacts3()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map { GatewayMapper.mapContact(it) }
                .flatMapCompletable { t: List<Contact> -> contactDao.insert(t) })
    }
}