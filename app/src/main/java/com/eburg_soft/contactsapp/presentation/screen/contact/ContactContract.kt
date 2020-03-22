package com.eburg_soft.contactsapp.presentation.screen.contact_fragment

import com.eburg_soft.contactsapp.presentation.base.BaseContract

interface ContactFragmentContract {
    interface View : BaseContract.View

    abstract class Presenter : BaseContract.Presenter<View>()
}