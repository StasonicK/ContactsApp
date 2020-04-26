package com.eburg_soft.contactsapp.presentation.base

import android.content.Context
import com.eburg_soft.contactsapp.common.App
import com.eburg_soft.contactsapp.di.screen.component.ScreenComponent
import com.eburg_soft.contactsapp.di.screen.module.ScreenContextModule
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseContract {
    interface View {
        fun getScreenComponent(context: Context): ScreenComponent =
            (context.applicationContext as App)
                .component
                .createScreenComponent(ScreenContextModule(context))
    }

    abstract class Presenter<V : View> {
        private val subscriptions = CompositeDisposable()
        protected var view: V? = null

        fun subscribe(subscription: Disposable) {
            subscriptions.add(subscription)
        }

        private fun unsubscribe() {
            subscriptions.clear()
        }

        open fun attach(view: V) {
            this.view = view
        }

        open fun detach() {
            unsubscribe()
        }
    }
}