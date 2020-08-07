package com.eburg_soft.contactsapp.di.screen.module

import android.content.Context
import com.eburg_soft.contactsapp.di.screen.component.ScreenContext
import com.eburg_soft.contactsapp.di.screen.scope.ScreenScope
import dagger.Module
import dagger.Provides

@Module
class ScreenContextModule(private val context: Context) {

    @Provides
    @ScreenScope
    @ScreenContext
    fun provideContext(): Context = context
}