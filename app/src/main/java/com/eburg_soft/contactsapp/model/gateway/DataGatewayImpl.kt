package com.eburg_soft.contactsapp.model.gateway

import com.eburg_soft.contactsapp.model.ApiClient
import com.eburg_soft.contactsapp.model.source.database.dao.ContactDao
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.utils.MyRxUtils
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DataGatewayImpl @Inject constructor(
    private val contactDao: ContactDao,
    private val apiClient: ApiClient,
    private val scheduler: MyRxUtils.BaseSchedulerProvider
) : DataGateway {

    override fun getAllContacts(): Single<List<Contact>> {
        return contactDao.getAllContacts()
            .subscribeOn(scheduler.io())
    }

    override fun eraseData(): Completable {
        return contactDao.deleteContacts()
    }

    override fun getContactById(id: String): Single<Contact> {
        return contactDao.getContactById(id)
            .subscribeOn(scheduler.io())
    }

    override fun syncData(): Completable {
        return apiClient.getContacts1()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.computation())
            .map { GatewayMapper.mapContact(it) }
            .flatMapCompletable { t: List<Contact> -> contactDao.insert(t) }
            .andThen(apiClient.getContacts2()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.computation())
                .map { GatewayMapper.mapContact(it) }
                .flatMapCompletable { t: List<Contact> -> contactDao.insert(t) })
            .andThen(apiClient.getContacts3()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.computation())
                .map { GatewayMapper.mapContact(it) }
                .flatMapCompletable { t: List<Contact> -> contactDao.insert(t) })
    }
}