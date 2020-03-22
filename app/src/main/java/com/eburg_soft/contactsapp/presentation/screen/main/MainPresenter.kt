package com.eburg_soft.contactsapp.presentation.screen.main

import com.eburg_soft.contactsapp.model.source.database.entity.Contact

class MainPresenter : MainContract.Presenter() {
    override fun onSearchQuerySubmit(query: String?, networkAvailable: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onContactClick(contact: Contact?, networkAvailable: Boolean) {
        TODO("Not yet implemented")
    }

    override fun loadContactsList() {
        TODO("Not yet implemented")
    }
}