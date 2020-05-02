package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.accounts.NetworkErrorException
import android.util.Log
import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import com.eburg_soft.contactsapp.model.gateway.data.DataGateway
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.utils.MyRxUtils
import io.reactivex.Single
import java.net.UnknownHostException
import java.util.Locale
import javax.inject.Inject

@ScreenScope
class ContactsListPresenter
@Inject constructor(
    private val gateway: DataGateway,
    private val scheduler: MyRxUtils.BaseSchedulerProvider
) : ContactsListContract.Presenter() {

    override fun onContactClick(contact: Contact) {
        view?.openContactView(contact)
    }

    override fun attach(view: ContactsListContract.View) {
        this.view = view
    }

    //calls in application start, after canceling query in search view, onRefresh()
    override fun loadContactsListFromDB() {
        view?.showLoading()
        subscribe(gateway.getAllContacts()
            .observeOn(scheduler.ui())
            .doOnNext { list: List<Contact> ->
                view?.submitList(list)
                view?.hideLoading()
                view?.scrollToRecyclerTopPosition()
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

    override fun onSearchQuerySubmit(query: String, list: ArrayList<Contact>) {
        if (query.trim().isEmpty()) {
            loadContactsListFromDB()
        } else {
            val newQuery = query.trim().toLowerCase(Locale.getDefault())

            view?.showLoading()

            subscribe(Single.just(list)
                .subscribeOn(scheduler.io())
                .map {
                    list.filter { contact ->
                        contact.contactName.toLowerCase(Locale.getDefault()).contains(newQuery)
                            .or(contact.contactPhone.contains(newQuery))
                    }
                }
                .observeOn(scheduler.ui())
                .subscribe({ list1 ->
                    view?.submitList(list1)
                    view?.hideLoading()
                    Log.d(ContactsListFragment.TAG, "query completed")
                }, { it ->
                    view?.showErrorMessage(it.message!!)
                    view?.hideLoading()
                })
            )
        }
    }

    override fun syncContacts() {
        view?.showLoading()
        subscribe(
            gateway.syncData()
                .observeOn(scheduler.ui())
                .subscribe({
                    view?.hideLoading()
                    Log.d(ContactsListFragment.TAG, "contacts synchronised")
                },
                    { error ->
                        when (error) {
                            is NetworkErrorException, is UnknownHostException -> {
                                view?.showNetworkErrorMessage()
                                view?.hideLoading()
                            }
                            else -> {
                                view?.showErrorMessage(error.message.toString())
                                error.printStackTrace()
                            }
                        }
                    })
        )
    }
}