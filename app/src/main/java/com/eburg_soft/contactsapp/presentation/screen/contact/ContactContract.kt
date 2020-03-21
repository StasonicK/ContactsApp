package com.eburg_soft.contactsapp.presentation.screen.contact

import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface ContactContract {
    interface View : BaseContract.View

    abstract class Presenter : BaseContract.Presenter<View>() {

    }
}