package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.presentation.base.BaseAdapter
import com.eburg_soft.contactsapp.presentation.base.BaseListFragment

/**
 * A simple [Fragment] subclass.
 */
class ContactsListFragment : BaseListFragment() {

    override fun createAdapterInstance(): BaseAdapter<*> {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }
}
