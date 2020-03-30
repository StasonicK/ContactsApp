package com.eburg_soft.contactsapp.presentation.screen.contact

import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import javax.inject.Inject

@ScreenScope
class ContactPresenter @Inject constructor(): ContactContract.Presenter() {
    override fun init(contact: Contact) {
        TODO("Not yet implemented")
    }

}