package com.eburg_soft.contactsapp.presentation.screen.contact_list

import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface ContactsListContract {
    interface View : BaseContract.View {
        fun showContacts()

        fun showLoading()

        fun hideLoading()

        fun showSearchView()

        fun showNetworkError()
    }

    abstract class Presenter : BaseContract.Presenter<View>() {
        fun onContactItemClick() {}

        fun onSearchQuerySubmit(query: String, networkAvailable: Boolean) {}

        fun loadContactsList() {}
    }
}