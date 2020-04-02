package com.eburg_soft.contactsapp.presentation.screen.contact_list

import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface ContactsListContract {
    interface View : BaseContract.View {

        fun addContact(contact: Contact)
        fun showContactsList()

        fun showLoading()

        fun hideLoading()

        fun notifyAdapter()

        fun refresh()

        fun showErrorMessage(error: String)

        fun openContactView(contact: Contact)
    }

    abstract class Presenter : BaseContract.Presenter<View>() {

        abstract fun onContactClick(contact: Contact)

        abstract fun loadContactsList()

        abstract fun syncContacts()

        abstract fun eraseContactsFromDB()

        abstract fun refreshContactsList()

        abstract fun onSearchQuerySubmit(query: String, contactList: ArrayList<Contact>)
    }
}