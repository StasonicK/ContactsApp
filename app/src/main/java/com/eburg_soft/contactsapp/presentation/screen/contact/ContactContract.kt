package com.eburg_soft.contactsapp.presentation.screen.contact

import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface ContactContract {
    interface View : BaseContract.View {
        fun callPhone()

        fun setupToolbar()

        fun bindViews()

        fun onBackPressed(): Boolean
    }

    abstract class Presenter : BaseContract.Presenter<View>() {
        abstract fun init(contact: Contact)
    }
}