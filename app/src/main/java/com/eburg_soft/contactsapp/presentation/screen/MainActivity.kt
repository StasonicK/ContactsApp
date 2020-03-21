package com.eburg_soft.contactsapp.presentation.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.R.layout
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListFragment

class MainActivity : AppCompatActivity() {

    lateinit var contactsListFragment: ContactsListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        if (contactsListFragment == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.frame_container, ContactsListFragment())
        }
    }
}
