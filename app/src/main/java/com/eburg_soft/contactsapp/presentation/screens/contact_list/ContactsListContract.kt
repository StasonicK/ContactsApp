package com.eburg_soft.contactsapp.presentation.screens.contact_list

import com.eburg_soft.contactsapp.models.source.database.entities.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface ContactsListContract {
    interface View : BaseContract.View {
        fun scrollToRecyclerTopPosition()

        fun showLoading()

        fun hideLoading()

        fun setWorkManager()

        fun submitList(list: List<Contact>)

        fun showNetworkErrorMessage()

        fun openContactView(contact: Contact)
    }

    abstract class Presenter : BaseContract.Presenter<View>() {

        abstract fun onContactClick(contact: Contact)

        abstract fun loadContactsListFromDB()

        abstract fun syncContacts()

        abstract fun eraseContactsFromDB()

        abstract fun onSearchQuerySubmit(query: String, contactList: ArrayList<Contact>)
    }
}