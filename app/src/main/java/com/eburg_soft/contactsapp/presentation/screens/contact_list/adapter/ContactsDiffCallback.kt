package com.eburg_soft.contactsapp.presentation.screens.contact_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eburg_soft.contactsapp.models.source.database.entities.Contact

class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.contactId == newItem.contactId
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}