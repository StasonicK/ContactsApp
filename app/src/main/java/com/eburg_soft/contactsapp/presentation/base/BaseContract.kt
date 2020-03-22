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
                .appComponent
                .createScreenComponent(ScreenContextModule(context))
    }

    abstract class Presenter<V : View> {
        private val subscriptions = CompositeDisposable()
        protected lateinit var view: View

        fun subscribe(subscription: Disposable) {
            subscriptions.add(subscription)
        }

        fun unsubscribe() {
            subscriptions.clear()
        }

        fun attach(view: View) {
            this.view = view
        }

        fun detach() {
            unsubscribe()
        }
    }
}