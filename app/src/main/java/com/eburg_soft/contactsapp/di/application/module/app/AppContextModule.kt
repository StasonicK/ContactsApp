package com.eburg_soft.contactsapp.di.application.module.app

import android.content.Context
import com.eburg_soft.contactsapp.di.application.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(private val context: Context) {

    @Provides
    @AppContext
    @AppScope
    fun provideContext(): Context = context
}