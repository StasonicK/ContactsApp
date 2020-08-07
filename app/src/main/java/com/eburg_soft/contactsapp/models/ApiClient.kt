package com.eburg_soft.contactsapp.models

import io.reactivex.Single
import retrofit2.http.GET

interface ApiClient {
    @GET("/SkbkonturMobile/mobile-test-droid/master/json/generated-01.json")
    fun getContacts1(): Single<List<ContactRes>>

    @GET("/SkbkonturMobile/mobile-test-droid/master/json/generated-02.json")
    fun getContacts2(): Single<List<ContactRes>>

    @GET("/SkbkonturMobile/mobile-test-droid/master/json/generated-03.json")
    fun getContacts3(): Single<List<ContactRes>>
}