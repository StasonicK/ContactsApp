package com.eburg_soft.contactsapp.presentation.screens.contact_list

import android.accounts.NetworkErrorException
import android.util.Log
import com.eburg_soft.contactsapp.di.screens.scope.ScreenScope
import com.eburg_soft.contactsapp.models.gateways.data.DataGateway
import com.eburg_soft.contactsapp.models.source.database.entities.Contact
import com.eburg_soft.contactsapp.utils.MyRxUtils
import io.reactivex.Single
import java.net.UnknownHostException
import java.util.Locale
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

@ScreenScope
class ContactsListPresenter
@Inject constructor(
    private val gateway: DataGateway,
    private val scheduler: MyRxUtils.BaseSchedulerProvider
) : ContactsListContract.Presenter() {

    companion object {

        const val TAG = "ContactsListPresenter"
    }

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
                Log.d(TAG, "contacts loaded")
            }
            .doOnError {
                view?.hideLoading()
                it.printStackTrace()
                Log.d(TAG, "showErrorMessage")
            }
            .subscribe()
        )
    }

    override fun eraseContactsFromDB() {
        subscribe(
            gateway.eraseData()
                .subscribe()
        )
        Log.d(TAG, "eraseContactsFromDB")
    }

    override fun onSearchQuerySubmit(query: String, list: ArrayList<Contact>) {
        if (query.trim().isEmpty()) {
            loadContactsListFromDB()
        } else {
            val newQuery = query.trim().toLowerCase(Locale.getDefault())

            view?.showLoading()

            subscribe(Single.just(list)
                .toObservable()
                .debounce(500, MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(scheduler.io())
                .map {
                    list
                        .filter { contact ->
                            contact.contactName.toLowerCase(Locale.getDefault()).contains(newQuery)
                                .or(contact.contactPhone.contains(newQuery))
                        }
                }
                .observeOn(scheduler.ui())
                .subscribe({ list1 ->
                    view?.submitList(list1)
                    view?.hideLoading()
                    Log.d(TAG, "query completed, $list")
                }, { it ->
                    view?.hideLoading()
                    Log.d(TAG, "showErrorMessage")
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
                    Log.d(TAG, "contacts synchronised")
                },
                    { error ->
                        when (error) {
                            is NetworkErrorException, is UnknownHostException -> {
                                view?.showNetworkErrorMessage()
                                view?.hideLoading()
                                Log.d(TAG, "showNetworkErrorMessage")
                            }
                            else -> {
                                error.printStackTrace()
                                Log.d(TAG, error.message.toString())
                            }
                        }
                    })
        )
    }
}