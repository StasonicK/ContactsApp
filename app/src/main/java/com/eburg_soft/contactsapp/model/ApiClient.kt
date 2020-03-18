package com.eburg_soft.contactsapp.model

import com.eburg_soft.contactsapp.base.BASE_URL_1
import com.eburg_soft.contactsapp.base.BASE_URL_2
import com.eburg_soft.contactsapp.base.BASE_URL_3
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {

    @POST(BASE_URL_1)
    fun callURL1(@Body request:Any): Single<List<Contact>>

    @POST(BASE_URL_2)
    fun callURL2(@Body request:Any): Single<List<Contact>>

    @POST(BASE_URL_3)
    fun callURL3(@Body request:Any): Single<List<Contact>>
}