package com.eburg_soft.contactsapp.presentation.screen.main

import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {
//        fun showLoading()
//
//        fun hideLoading()
//
//        fun notifyAdapter()
//
//        fun showErrorMessage(error: String?)
//
//        fun showContactsListByQuery(contacts: List<Contact>){}
    }

    abstract class Presenter : BaseContract.Presenter<View>() {
//      abstract  fun loadContactsListByQuery(query: String?, networkAvailable: Boolean)
    }
}