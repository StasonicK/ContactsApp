package com.eburg_soft.contactsapp.common

import android.app.Application
import com.eburg_soft.contactsapp.di.application.component.AppComponent
import com.eburg_soft.contactsapp.di.application.component.DaggerAppComponent
import com.eburg_soft.contactsapp.di.application.module.app.AppContextModule
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent().inject(this)
    }

    private fun appComponent(): AppComponent {
        appComponent = DaggerAppComponent.builder()
            .appContextModule(AppContextModule(applicationContext))
            .build()
        return appComponent
    }
}