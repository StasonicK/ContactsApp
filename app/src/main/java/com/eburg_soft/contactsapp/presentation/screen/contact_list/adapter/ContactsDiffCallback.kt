package com.eburg_soft.contactsapp.presentation.screen.contact_list

import androidx.recyclerview.widget.DiffUtil
import com.eburg_soft.contactsapp.model.source.database.entity.Contact

class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.contactId == newItem.contactId
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}