package com.eburg_soft.contactsapp.common

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
//todo add appComponent
    }

    private fun initializeDagger() {
        //todo write appComponent
    }
}