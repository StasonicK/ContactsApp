package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.content.Context
import android.util.Log
import com.eburg_soft.contactsapp.di.application.module.app.AppContext
import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import com.eburg_soft.contactsapp.model.gateway.DataGateway
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.utils.MyNetworkUtils
import com.eburg_soft.contactsapp.utils.MyRxUtils
import io.reactivex.Flowable
import java.util.Locale
import javax.inject.Inject

@ScreenScope
class ContactsListPresenter
@Inject constructor(
    private val gateway: DataGateway,
    private val scheduler: MyRxUtils.BaseSchedulerProvider,
    @AppContext private val context: Context
) : ContactsListContract.Presenter() {


    override fun onContactClick(contact: Contact) {
        view?.openContactView(contact)
    }

    override fun loadContactsList() {
        subscribe(gateway.getAllContacts()
            .observeOn(scheduler.computation())
            .toFlowable()
            .flatMap { Flowable.fromIterable(it) }
            .map { t: Contact ->
                view?.addContact(
                    Contact(
                        t.contactId,
                        t.contactName,
                        t.contactPhone,
                        t.contactHeight,
                        t.contactBiography,
                        t.contactTemperament,
                        t.contactEducationStart,
                        t.contactEducationEnd
                    )
                )
            }
            .observeOn(scheduler.ui())
            .doOnComplete {
                view?.hideLoading()
                view?.notifyAdapter()
                Log.d(ContactsListFragment.TAG, "contacts loaded")
            }
            .doOnError {
                view?.showErrorMessage(it.message.toString())
                view?.hideLoading()
                it.printStackTrace()
            }
            .subscribe()
        )
    }

    override fun eraseContactsFromDB() {
        subscribe(
            gateway.eraseData()
                .subscribe()
        )
    }

    override fun refreshContactsList() {
        loadContactsList()
    }

    override fun onSearchQuerySubmit(query: String, contactsList: ArrayList<Contact>) {
        if (query.trim().isNotEmpty()) {
            val newQuery = query.trim().toLowerCase(Locale.getDefault())

            if (MyNetworkUtils.isNetworkAvailable(context)) {
                view?.showLoading()
                subscribe(Flowable.just(contactsList)
                    .subscribeOn(scheduler.io())
                    .flatMap { t: ArrayList<Contact> -> Flowable.fromIterable(t) }
                    .filter { t: Contact ->
                        t.contactName.contains(newQuery).or(t.contactPhone.contains(newQuery))
                    }
                    .map { t: Contact ->
                        view?.addContact(
                            Contact(
                                t.contactId,
                                t.contactName,
                                t.contactPhone,
                                t.contactHeight,
                                t.contactBiography,
                                t.contactTemperament,
                                t.contactEducationStart,
                                t.contactEducationEnd
                            )
                        )
                    }
                    .observeOn(scheduler.ui())
                    .doOnComplete {
                        view?.hideLoading()
                        view?.notifyAdapter()
                        Log.d(ContactsListFragment.TAG, "contacts loaded")
                    }
                    .doOnError {
                        view?.showErrorMessage(it.message.toString())
                        view?.hideLoading()
                        it.printStackTrace()
                    }
                    .subscribe()
                )
            }
        }
    }

    override fun syncContacts() {
        view?.showLoading()
        subscribe(
            gateway.syncData()
                .observeOn(scheduler.ui())
                .doOnComplete {
                    view?.hideLoading()
                    Log.d(ContactsListFragment.TAG, "contacts syncronized")
                }
                .doOnError { view?.showErrorMessage(it.message.toString()) }
                .subscribe()
        )
    }
}