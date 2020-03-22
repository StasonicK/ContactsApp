package com.eburg_soft.contactsapp.presentation.screen.main

import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
        fun showContactsList(contacts: List<Contact?>?)

        fun showLoading()

        fun hideLoading()

        fun showNetworkError()

        fun openContactView(contact: Contact?)
    }

    abstract class Presenter : BaseContract.Presenter<View>() {
       abstract fun onSearchQuerySubmit(query: String?, networkAvailable: Boolean)

      abstract  fun onContactClick(contact: Contact?, networkAvailable: Boolean)

      abstract  fun loadContactsList()
    }
}