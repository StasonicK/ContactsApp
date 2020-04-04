package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.content.Context
import android.util.Log
import com.eburg_soft.contactsapp.di.application.module.app.AppContext
import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import com.eburg_soft.contactsapp.model.gateway.DataGateway
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.utils.MyRxUtils
import io.reactivex.Single
import retrofit2.HttpException
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

    //calls in application start, after canceling query in search view, onRefresh()
    override fun loadContactsListFromDB() {
        view?.showLoading()
        subscribe(gateway.getAllContacts()
            .observeOn(scheduler.computation())
            .toFlowable()
            .observeOn(scheduler.ui())
            .doOnNext { list: List<Contact> ->
                view?.submitList(list)
                view?.addContacts(list)
            }
            .doOnComplete {
                view?.hideLoading()
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
        if (query.trim().isNotEmpty()) {
            val newQuery = query.trim().toLowerCase(Locale.getDefault())

            view?.showLoading()

//            subscribe(Single.just(list)
//                .map {
//                    list.filter { contact ->
//                        contact.contactName.contains(newQuery).or(contact.contactPhone.contains(newQuery))
//                    }
//                }
//                .observeOn(scheduler.ui())
//                .doOnSuccess { list1: List<Contact> ->
//                    view?.submitList(list1)
//                }
//                .subscribe({
//                    view?.hideLoading()
//                    Log.d(ContactsListFragment.TAG, "query completed")
//                }, {
//                    view?.showErrorMessage(it.toString())
//                    view?.hideLoading()
//                })
//            )

            subscribe(Single.just(list)
                .map {
                    list.filter { contact ->
//                        contact.contactName.toLowerCase(Locale.getDefault()).contains(newQuery).or(contact.contactPhone.contains(newQuery))
                        contact.contactName.toLowerCase(Locale.getDefault())
                            .contains(newQuery) || contact.contactPhone.contains(
                            newQuery
                        )
                    }
                }
                .subscribeOn(scheduler.io())
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
                .doOnComplete {
                    view?.hideLoading()
                    Log.d(ContactsListFragment.TAG, "contacts syncronized")
                }
                .doOnError { error ->
                    if (error is HttpException) {
                        view?.showNetworkErrorMessage()
                    } else {
                        view?.showErrorMessage(error.message.toString())
                        error.printStackTrace()
                    }
                }
                .subscribe()
        )
    }
}