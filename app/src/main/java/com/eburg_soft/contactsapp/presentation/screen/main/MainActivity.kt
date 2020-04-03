package com.eburg_soft.contactsapp.presentation.screen.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.R.layout
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListFragment
import kotlinx.android.synthetic.main.toolbar.toolbar

class MainActivity : AppCompatActivity() {

    private val BUNDLE_SEARCH_QUERY: String = "searchQuery"
    private var searchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.frame_container,
                    ContactsListFragment(), ContactsListFragment.TAG + " created"
                )
                .addToBackStack(ContactsListFragment.TAG)
                .commit()
        } else {
            searchQuery = savedInstanceState.getString(BUNDLE_SEARCH_QUERY).toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(BUNDLE_SEARCH_QUERY, searchQuery)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        fragmentManager.findFragmentById(R.id.frame_container)
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else finish()
    }
}
