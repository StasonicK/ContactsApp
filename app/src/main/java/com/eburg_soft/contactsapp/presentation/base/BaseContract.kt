package com.eburg_soft.contactsapp.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseContract {
    interface View

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