package com.eburg_soft.contactsapp

import com.eburg_soft.contactsapp.model.ApiClient
import com.eburg_soft.contactsapp.model.gateway.DataGateway
import com.eburg_soft.contactsapp.model.gateway.DataGatewayImpl
import com.eburg_soft.contactsapp.model.source.database.dao.ContactDao
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListContract
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListPresenter
import com.eburg_soft.contactsapp.utils.MyRxUtils
import org.junit.*
import org.mockito.*
import org.mockito.Mockito.*

class ContactsListPresenterTest {
//    @get:Rule
//    lateinit var mockitoRule: MockitoRule

    private lateinit var presenter: ContactsListPresenter

    @Mock
    lateinit var mockGateway: DataGateway

    @Mock
    lateinit var mockApiClient: ApiClient

    @Mock
    lateinit var mockContactDao: ContactDao

    @Mock
    lateinit var mockRx: MyRxUtils.BaseSchedulerProvider

    @Mock
    lateinit var mockView: ContactsListContract.View

//    @Mock
//    lateinit var mockContact: Contact

    @Before
     fun setUp() {
        MockitoAnnotations::initMocks
        mockGateway = DataGatewayImpl(mockContactDao, mockApiClient, mockRx)
        presenter = ContactsListPresenter(mockGateway, mockRx)
        presenter.attach(mockView)
    }

    @Test
    fun onContactClick() {
        val mockContact = mock(Contact::class.java)
        presenter.onContactClick(mockContact)

        verify(mockView).openContactView(mockContact)
    }

    @Test
    fun loadContactsListFromDB() {
//        `when`(presenter.onContactClick(mockContact)).then(mockView.)
    }

    @Test
    fun eraseContactsFromDB() {
    }

    @Test
    fun onSearchQuerySubmit() {
    }

    @Test
    fun syncContacts() {
    }

    @After
    internal fun tearDown() {
        presenter.detach()
    }
}