package com.eburg_soft.contactsapp.di.screens.module

import com.eburg_soft.contactsapp.di.screens.scope.ScreenScope
import com.eburg_soft.contactsapp.presentation.screens.contact_list.ContactsListContract
import com.eburg_soft.contactsapp.presentation.screens.contact_list.ContactsListPresenter
import dagger.Binds
import dagger.Module

@Module
interface ScreenModule {

    @Binds
    @ScreenScope
    fun provideContactsListPresenter(presenter: ContactsListPresenter): ContactsListContract.Presenter
}