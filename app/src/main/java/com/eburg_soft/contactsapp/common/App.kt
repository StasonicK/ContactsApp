package com.eburg_soft.contactsapp.common

import android.app.Application
import com.eburg_soft.contactsapp.di.application.component.AppComponent
import com.eburg_soft.contactsapp.di.application.component.DaggerAppComponent
import com.eburg_soft.contactsapp.di.application.module.app.AppContextModule

class App : Application() {

//    companion object {
//        lateinit var appComponent: AppComponent
//    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
//        appComponent().inject(this)
    }

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appContextModule(AppContextModule(applicationContext))
            .build()
    }

//    fun appComponent(): AppComponent {
//        appComponent = DaggerAppComponent.builder()
//            .appContextModule(AppContextModule(applicationContext))
//            .build()
//        return appComponent
//    }
}