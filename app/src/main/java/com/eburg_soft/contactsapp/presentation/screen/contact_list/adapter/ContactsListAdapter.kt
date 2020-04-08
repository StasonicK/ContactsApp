package com.eburg_soft.contactsapp.presentation.screen.contact_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.screen.contact_list.adapter.ContactsListAdapterList.ContactsViewHolder
import kotlinx.android.synthetic.main.recycler_view_item.view.text_height
import kotlinx.android.synthetic.main.recycler_view_item.view.text_name
import kotlinx.android.synthetic.main.recycler_view_item.view.text_phone

class ContactsListAdapterList(val listener: OnContactItemClickListener? = null) :
    ListAdapter<Contact, ContactsViewHolder>(ContactsDiffCallback()) {

    interface OnContactItemClickListener {
        fun onContactClick(contact: Contact)
    }

    inner class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var id: String = ""
        var name: String = ""
        var height: String = ""
        var biography: String = ""
        var temperament: String = ""
        var educationStart: String = ""
        var educationEnd: String = ""

        fun bind(item: Contact) {

            let {
                item

                itemView.text_name.text = item.contactName
                itemView.text_phone.text = item.contactPhone
                itemView.text_height.text = item.contactHeight.toString()

                id = item.contactId
                name = item.contactName
                height = item.contactHeight.toString()
                biography = item.contactBiography
                temperament = item.contactTemperament.type
                educationStart = item.contactEducationStart
                educationEnd = item.contactEducationEnd

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