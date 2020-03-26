package com.eburg_soft.contactsapp.di.screen.component

import com.eburg_soft.contactsapp.di.screen.module.ScreenContextModule
import com.eburg_soft.contactsapp.di.screen.module.ScreenModule
import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import com.eburg_soft.contactsapp.presentation.screen.contact.ContactFragment
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = arrayOf(ScreenContextModule::class, ScreenModule::class))
interface ScreenComponent {

    fun inject(contactFragment: ContactFragment)
    fun inject(contactsListFragment: ContactsListFragment)
}