package com.eburg_soft.contactsapp.di.application.component

import com.eburg_soft.contactsapp.common.App
import com.eburg_soft.contactsapp.di.application.module.app.AppContextModule
import com.eburg_soft.contactsapp.di.application.module.data.DatabaseModule
import com.eburg_soft.contactsapp.di.application.module.gateway.GatewayModule
import com.eburg_soft.contactsapp.di.application.module.network.NetworkModule
import com.eburg_soft.contactsapp.di.application.module.rx.RxModule
import com.eburg_soft.contactsapp.di.application.scope.AppScope
import com.eburg_soft.contactsapp.di.screen.component.ScreenComponent
import com.eburg_soft.contactsapp.di.screen.module.ScreenContextModule
import dagger.Component

@AppScope
@Component(
    modules = [AppContextModule::class, NetworkModule::class, DatabaseModule::class, GatewayModule::class, RxModule::class]
)
interface AppComponent {

    fun createScreenComponent(screenContextModule: ScreenContextModule): ScreenComponent

    fun inject(app: App)
}