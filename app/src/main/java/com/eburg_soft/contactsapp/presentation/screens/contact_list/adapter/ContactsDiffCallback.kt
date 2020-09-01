package com.eburg_soft.contactsapp.presentation.screens.contact_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eburg_soft.contactsapp.models.source.database.entities.Contact

class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.contactId == newItem.contactId &&
                oldItem.contactName == newItem.contactName &&
                oldItem.contactPhone == newItem.contactPhone &&
                oldItem.contactBiography == newItem.contactBiography &&
                oldItem.contactHeight == newItem.contactHeight &&
                oldItem.contactTemperament == newItem.contactTemperament &&
                oldItem.contactEducationStart == newItem.contactEducationStart &&
                oldItem.contactEducationEnd == newItem.contactEducationEnd
    }
}