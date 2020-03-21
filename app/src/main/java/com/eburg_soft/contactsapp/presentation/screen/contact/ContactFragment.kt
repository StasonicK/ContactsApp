package com.eburg_soft.contactsapp.presentation.screen.contact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.presentation.base.BaseFragment

/**
 * Class [ContactFragment] shows contact information, has clickable phone number.
 */
class ContactFragment : BaseFragment(R.layout.fragment_contact), ContactContract.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }
}
