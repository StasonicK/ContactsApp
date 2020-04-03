package com.eburg_soft.contactsapp.di.screen.module

import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListContract
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListPresenter
import dagger.Binds
import dagger.Module

@Module
interface ScreenModule {

    @Binds
    @ScreenScope
    fun provideContactsListPresenter(presenter: ContactsListPresenter): ContactsListContract.Presenter

//    @Binds
//    @ScreenScope
//    fun provideContactsPresenter(presenter: ContactPresenter): ContactContract.Presenter
}