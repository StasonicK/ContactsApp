package com.eburg_soft.contactsapp.presenter

import com.eburg_soft.contactsapp.database.ContactTestHelper
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
import org.mockito.junit.*

class ContactsListPresenterTest {
    @get:Rule
   private val mockitoRule = MockitoJUnit.rule()

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

    @Mock
    lateinit var mockContact: Contact

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

        `when`(presenter.onContactClick(any()))

        verify(mockView).openContactView(mockContact)
    }

    @Test
    fun loadContactsListFromDBSuccess() {
        val contacts = ContactTestHelper.createListOfContacts()
        `when`(presenter.loadContactsListFromDB())
        
        presenter.loadContactsListFromDB()
        verify(mockView.showLoading())
        verify(mockView.submitList(contacts))
    }

    @Test
    fun loadContactsListFromDBFailed() {
//        `when`(presenter.onContactClick(mockContact)).then(mockView.)
    }


    @Test
    fun eraseContactsFromDB() {

    }

    @Test
    fun onSearchQuerySubmitSuccess() {

    }

    @Test
    fun onSearchQuerySubmitFailed() {

    }

    @Test
    fun syncContactsSuccess() {

    }

    @Test
    fun syncContactsFailed() {

    }

    @After
    internal fun tearDown() {
        presenter.detach()
    }
}