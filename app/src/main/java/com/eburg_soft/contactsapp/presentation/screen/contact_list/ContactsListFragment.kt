package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.os.Bundle
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseAdapter
import com.eburg_soft.contactsapp.presentation.base.BaseListFragment
import com.google.android.material.snackbar.Snackbar
import java.util.Date

/**
 * Class [ContactsListFragment] show the list of contacts and assist to find contacts by phone number
 * or name.
 */

class ContactsListFragment : BaseListFragment(R.layout.fragment_contacts_list),
    ContactsAdapter.OnContactItemClickListener, ContactsListContract.View {


    private val listAdapter = ContactsAdapter(this)

    override fun createAdapterInstance(): BaseAdapter<*> {
        return ContactsAdapter()
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
        Snackbar.make(recyclerView, R.string.network_error, Snackbar.LENGTH_LONG).show()
    }
}
