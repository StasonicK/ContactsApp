package com.eburg_soft.contactsapp.models.gatewais.data

import com.eburg_soft.contactsapp.models.ApiClient
import com.eburg_soft.contactsapp.models.ContactRes
import com.eburg_soft.contactsapp.models.gatewais.GatewayMapper
import com.eburg_soft.contactsapp.models.source.database.daos.ContactDao
import com.eburg_soft.contactsapp.models.source.database.entities.Contact
import com.eburg_soft.contactsapp.utils.MyRxUtils
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class DataGatewayImpl @Inject constructor(
    private val contactDao: ContactDao,
    private val apiClient: ApiClient,
    private val scheduler: MyRxUtils.BaseSchedulerProvider
) : DataGateway {

    override fun getAllContacts(): Flowable<List<Contact>> {
        return contactDao.getAllContacts()
            .subscribeOn(scheduler.io())
    }

    override fun eraseData(): Completable {
        return contactDao.deleteContacts()
            .subscribeOn(scheduler.io())
    }

    override fun syncData(): Completable {
        return Single.zip(
            apiClient.getContacts1(),
            apiClient.getContacts2(),
            apiClient.getContacts3(),
            Function3<List<ContactRes>, List<ContactRes>, List<ContactRes>, List<ContactRes>>
            { list1, list2, list3 ->
                val list = ArrayList<ContactRes>()
                list.apply {
                    addAll(list1)
                    addAll(list2)
                    addAll(list3)
                }
                list
            }).flatMapCompletable { t ->
            contactDao.insert(
                GatewayMapper.mapContact(
                    t
                )
            )
        }
            .subscribeOn(scheduler.io())
    }
}