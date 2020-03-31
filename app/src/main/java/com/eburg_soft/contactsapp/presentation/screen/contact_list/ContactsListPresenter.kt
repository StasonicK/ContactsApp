package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.text.TextUtils
import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import com.eburg_soft.contactsapp.model.gateway.DataGateway
import com.eburg_soft.contactsapp.model.source.database.dao.ContactDao
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.utils.MyRxUtils
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ScreenScope
class ContactsListPresenter
@Inject constructor(
    private val gateway: DataGateway,
    private val scheduler: MyRxUtils.BaseSchedulerProvider,
    private val contactDao: ContactDao
) : ContactsListContract.Presenter() {

    override fun onContactClick(contact: Contact) {
        view?.openContactView(contact)
    }

    override fun loadContactsList() {
        syncContacts()
        subscribe(gateway.getAllContacts()
            .observeOn(scheduler.computation())
            .toFlowable()
            .flatMap { Flowable.fromIterable(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                view?.addCurrency(
                    Contact(
                        it.contactId,
                        it.contactName,
                        it.contactPhone,
                        it.contactHeingt,
                        it.contactBiography,
                        it.contactTemperament,
                        it.contactEducationStart,
                        it.contactEducationEnd
                    )
                )
            }
            .doOnComplete {
                view?.hideLoading()
            }
            .subscribe({
                view?.hideLoading()
                view?.notifyAdapter()
            }, {
                view?.showErrorMessage(it.message)
                view?.hideLoading()
                it.printStackTrace()
            })
        )
    }

    override fun eraseContactsFromDB() {
        subscribe(
            gateway.eraseData()
                .observeOn(scheduler.computation())
                .subscribe()
        )
    }

    override fun refreshContactsList() {
        loadContactsList()
        view?.notifyAdapter()
    }

    override fun onSearchQuerySubmit(query: String?, networkAvailable: Boolean) {
        if (!TextUtils.isEmpty(query)) {
            if (networkAvailable) {
                subscribe(
                    setContactsListInAdapter(contactDao.getContactsByName(query ?: ""))

                )
                subscribe(
                    setContactsListInAdapter(contactDao.getContactsByPhone(query ?: ""))
                )
            }
        }
    }

    private fun setContactsListInAdapter(maybe: Maybe<List<Contact>>): Disposable {
        return maybe
            .toFlowable()
            .observeOn(scheduler.computation())
            .flatMap { Flowable.fromIterable(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                view?.addCurrency(
                    Contact(
                        it.contactId,
                        it.contactName,
                        it.contactPhone,
                        it.contactHeingt,
                        it.contactBiography,
                        it.contactTemperament,
                        it.contactEducationStart,
                        it.contactEducationEnd
                    )
                )
            }
            .doOnComplete {
                view?.hideLoading()
            }
            .subscribe({
                view?.hideLoading()
                view?.notifyAdapter()
            }, {
                view?.showErrorMessage(it.message)
                view?.hideLoading()
                it.printStackTrace()
            })
    }

    private fun syncContacts() {
        view?.showLoading()
        subscribe(
            gateway.syncData()
                .observeOn(scheduler.computation())
                .doOnComplete {
                    view?.hideLoading()
                }
                .doOnError { view?.showErrorMessage(it.message) }
                .subscribe()
        )
    }
}