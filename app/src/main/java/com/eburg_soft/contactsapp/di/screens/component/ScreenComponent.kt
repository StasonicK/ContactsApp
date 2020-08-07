package com.eburg_soft.contactsapp.di.screens.component

import com.eburg_soft.contactsapp.di.screens.module.ScreenContextModule
import com.eburg_soft.contactsapp.di.screens.module.ScreenModule
import com.eburg_soft.contactsapp.di.screens.scope.ScreenScope
import com.eburg_soft.contactsapp.presentation.screens.contact_list.ContactsListFragment
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [ScreenContextModule::class, ScreenModule::class])
interface ScreenComponent {

    fun inject(fragment: ContactsListFragment)
}