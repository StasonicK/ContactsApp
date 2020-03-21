package com.eburg_soft.contactsapp.model

import io.reactivex.Single
import retrofit2.http.GET

interface ApiClient {
    @GET("/json/generated-01.json")
    fun getContacts1(): Single<List<ContactRes>>

    @GET("/json/generated-02.json")
    fun getContacts2(): Single<List<ContactRes>>

    @GET("/json/generated-03.json")
    fun getContacts3(): Single<List<ContactRes>>
}