package com.eburg_soft.contactsapp.presentation.screen.contact_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.presentation.base.BaseFragment

/**
 * Class [ContactFragment] shows contact information, has clickable phone number.
 */
class ContactFragment : BaseFragment(R.layout.fragment_contact), ContactFragmentContract.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        ButterKnife.bind(this, view)


        return view
    }
}
