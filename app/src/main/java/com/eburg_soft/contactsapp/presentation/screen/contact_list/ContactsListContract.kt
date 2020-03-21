package com.eburg_soft.contactsapp.presentation.screen.contact_list

import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface ContactContract {
    interface View : BaseContract.View {
        fun showContacts()
        fun showSearchView()
    }

    abstract class Presenter : BaseContract.Presenter<View>() {
        fun onContactItemClick() {}
        fun onSearchQuerySubmit(query: String, networkAvailable: Boolean) {        }

        fun loadContactsList()

    }
}