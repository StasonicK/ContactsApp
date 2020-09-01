package com.eburg_soft.contactsapp.presentation.screens.contact_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.models.source.database.entities.Contact
import com.eburg_soft.contactsapp.presentation.screens.contact_list.adapter.ContactsListAdapter.ContactsViewHolder
import kotlinx.android.synthetic.main.recycler_view_item.view.text_height
import kotlinx.android.synthetic.main.recycler_view_item.view.text_name
import kotlinx.android.synthetic.main.recycler_view_item.view.text_phone

class ContactsListAdapter(val listener: OnContactItemClickListener? = null) :
    ListAdapter<Contact, ContactsViewHolder>(ContactsDiffCallback()) {

    interface OnContactItemClickListener {

        fun onContactClick(contact: Contact)
    }

    inner class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Contact) {

            let {
                itemView.text_name.text = item.contactName
                itemView.text_phone.text = item.contactPhone
                itemView.text_height.text = item.contactHeight.toString()

                itemView.setOnClickListener { listener?.onContactClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}