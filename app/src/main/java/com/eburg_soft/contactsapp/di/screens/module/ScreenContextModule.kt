package com.eburg_soft.contactsapp.di.screens.module

import android.content.Context
import com.eburg_soft.contactsapp.di.screens.component.ScreenContext
import com.eburg_soft.contactsapp.di.screens.scope.ScreenScope
import dagger.Module
import dagger.Provides

@Module
class ScreenContextModule(private val context: Context) {

    @Provides
    @ScreenScope
    @ScreenContext
    fun provideContext(): Context = context
}