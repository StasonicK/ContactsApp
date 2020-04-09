package com.eburg_soft.contactsapp

import com.eburg_soft.contactsapp.model.ApiClient
import com.eburg_soft.contactsapp.model.ContactEducationPeriodRes
import com.eburg_soft.contactsapp.model.ContactRes
import com.eburg_soft.contactsapp.model.gateway.DataGatewayImpl
import com.eburg_soft.contactsapp.model.source.database.dao.ContactDao
import com.eburg_soft.contactsapp.utils.MyRxUtils
import io.reactivex.Single
import okhttp3.internal.immutableListOf
import org.junit.*
import org.junit.Assert.*
//import org.mockito.*
//import org.mockito.junit.*

class DataGatewayImplTest {
//    @get:Rule
//    lateinit var mockitoRule: MockitoRule
//
//    @Mock
//    lateinit var apiClient: ApiClient
//
//    @Mock
//    lateinit var contactDto: ContactDao
//
//    @Mock
//    lateinit var rx: MyRxUtils.TrampolineSchedulerProvider

// lateinit var   dataGatewayImpl: DataGatewayImpl = DataGatewayImpl(contactDto, apiClient, rx)

//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//    }

//    @Before

//    @Test
//    fun shouldReturnExpectedContact() {
//        val expected = immutableListOf(
//            ContactRes(
//                "a1", "name", "2115541", 20.0, "biography", "CHOLERIC",
//                ContactEducationPeriodRes("01.01.2010", "01.01.2014")
//            )
//        )
//        Mockito.`when`(apiClient.getContacts1()).thenReturn(Single.just(expected))
//
//    }
}