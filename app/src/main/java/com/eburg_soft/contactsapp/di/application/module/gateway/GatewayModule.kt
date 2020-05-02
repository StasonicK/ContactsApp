package com.eburg_soft.contactsapp.di.application.module.gateway

import com.eburg_soft.contactsapp.di.application.scope.AppScope
import com.eburg_soft.contactsapp.model.gateway.data.DataGateway
import com.eburg_soft.contactsapp.model.gateway.data.DataGatewayImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GatewayModule {

    @Binds
    @AppScope
    abstract fun dataGateway(dataGatewayImpl: DataGatewayImpl): DataGateway
}