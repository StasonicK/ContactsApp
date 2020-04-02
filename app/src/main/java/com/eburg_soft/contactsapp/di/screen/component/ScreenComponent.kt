package com.eburg_soft.contactsapp.di.screen.component

import com.eburg_soft.contactsapp.di.screen.module.ScreenContextModule
import com.eburg_soft.contactsapp.di.screen.module.ScreenModule
import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import com.eburg_soft.contactsapp.presentation.screen.contact.ContactFragment
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [ScreenContextModule::class, ScreenModule::class])
interface ScreenComponent {

    fun inject(fragment: ContactFragment)
    fun inject(fragment: ContactsListFragment)
}