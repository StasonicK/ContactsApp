package com.eburg_soft.contactsapp.presentation.screen.contact_list

import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseAdapter
import com.eburg_soft.contactsapp.presentation.base.BaseListFragment
import java.util.Date

/**
 * Class [ContactsListFragment] show the list of contacts and assist to find contacts by phone number
 * or name.
 */

class ContactsListFragment : BaseListFragment(R.layout.fragment_contacts_list),
    ContactsAdapter.onContactItemClickListener, ContactsListContract.View {

    private val listAdapter = ContactsAdapter(this)

    var lastLauchTime: Date = TODO()

    override fun createAdapterInstance(): BaseAdapter<*> {
        TODO("Not yet implemented")
    }

    override fun onContactsListItemClick(contact: Contact) {

    }

    override fun showContacts() {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showSearchView() {
        TODO("Not yet implemented")
    }

    override fun showNetworkError() {
        TODO("Not yet implemented")
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
//    }

}
