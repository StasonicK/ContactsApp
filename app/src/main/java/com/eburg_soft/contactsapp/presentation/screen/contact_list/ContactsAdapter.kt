package com.eburg_soft.contactsapp.ui.contact_list

import android.view.View
import android.view.ViewGroup
import com.eburg_soft.contactsapp.base.BaseAdapter
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.ui.contact_list.ContactsAdapter.ContactViewHolder

class ContactsAdapter: BaseAdapter<ContactViewHolder>() {
private lateinit var contactsList:ArrayList<Contact>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        TODO("Not yet implemented")
    }

    class ContactViewHolder(view: View):BaseAdapter.BaseViewHolder(view){
        override fun bind(item: Any) {
            TODO("Not yet implemented")
        }
    }
}