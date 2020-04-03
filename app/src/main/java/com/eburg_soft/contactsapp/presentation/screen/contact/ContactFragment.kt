package com.eburg_soft.contactsapp.presentation.screen.contact

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseFragment
import com.eburg_soft.contactsapp.presentation.screen.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.progressbar
import kotlinx.android.synthetic.main.fragment_contact.text_biography_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_education_period_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_name_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_phone_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_temperament_in_contact
import javax.inject.Inject

/**
 * Class [ContactFragment] shows contact information, has clickable phone number.
 */
class ContactFragment @Inject constructor() : BaseFragment(R.layout.fragment_contact) {

    private var contact: Contact? = null

    val REQUEST_CODE_PERMISSION_CALL_PHONE: Int = 0

    init {
//        getScreenComponent(requireContext()).inject(this)
    }

    companion object {
        const val TAG = "ContactFragment"
        private const val CONTACT = "Contact"
        private const val BUNDLE_CONTACT_NAME = "contact_name"
        private const val BUNDLE_CONTACT_PHONE = "contact_phone"
        private const val BUNDLE_CONTACT_TEMPERAMENT = "contact_temperament"
        private const val BUNDLE_CONTACT_EDUCATION_START = "education_start"
        private const val BUNDLE_CONTACT_EDUCATION_END = "education_end"
        private const val BUNDLE_CONTACT_BIOGRAPHY = "contact_biography"

        @JvmStatic
        fun NewInstance(contact: Contact) =
            ContactFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CONTACT, contact)
                }
            }
    }

    //region ====================== Life circle ======================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().progressbar.visibility = View.INVISIBLE
        bindViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true

        setupToolbar()

        contact = requireArguments().getParcelable(CONTACT) as Contact?
        savedInstanceState?.let { state ->
            (contact as Contact).contactName = state.getString(BUNDLE_CONTACT_NAME, contact!!.contactName).toString()
            contact!!.contactPhone = state.getString(BUNDLE_CONTACT_PHONE, contact!!.contactPhone).toString()
            contact!!.contactTemperament =
                state.getString(BUNDLE_CONTACT_TEMPERAMENT, contact!!.contactTemperament).toString()
            contact!!.contactEducationStart =
                state.getString(BUNDLE_CONTACT_EDUCATION_START, contact!!.contactEducationStart).toString()
            contact!!.contactEducationEnd =
                state.getString(BUNDLE_CONTACT_EDUCATION_END, contact!!.contactEducationEnd).toString()
            contact!!.contactBiography =
                state.getString(BUNDLE_CONTACT_BIOGRAPHY, contact!!.contactBiography).toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_CONTACT_NAME, contact?.contactName)
        outState.putString(BUNDLE_CONTACT_PHONE, contact?.contactPhone)
        outState.putString(BUNDLE_CONTACT_TEMPERAMENT, contact?.contactTemperament)
        outState.putString(BUNDLE_CONTACT_EDUCATION_START, contact?.contactEducationStart)
        outState.putString(BUNDLE_CONTACT_EDUCATION_END, contact?.contactEducationEnd)
        outState.putString(BUNDLE_CONTACT_BIOGRAPHY, contact?.contactBiography)
    }

    //endregion

    // Invokes call phone by clicking a phone number
    private fun callPhone() {
        val permissionStatus = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            startActivity(getCallIntent())
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CODE_PERMISSION_CALL_PHONE
            )
        }
    }

    private fun setupToolbar() {
        val actionBar: ActionBar? = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.title = ""
    }

    private fun bindViews() {
        text_name_in_contact.text = contact?.contactName
        text_phone_in_contact.text = contact?.contactPhone
        text_temperament_in_contact.text = contact?.contactTemperament
        val education = "${contact?.contactEducationStart} - ${contact?.contactEducationEnd}"
        text_education_period_in_contact.text = education
        text_biography_in_contact.text = contact?.contactBiography

        text_phone_in_contact.setOnClickListener { callPhone() }
    }

    private fun getCallIntent(): Intent {
        val phone = text_phone_in_contact.text
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel: $phone")
        return intent
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION_CALL_PHONE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(getCallIntent())
                }
            }
        }
    }

    //TODO check this method!
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        return if (id == android.R.id.home) {
//            val fragmentManager = (activity as MainActivity).supportFragmentManager
//            fragmentManager.findFragmentById(R.id.frame_container)
//            if (fragmentManager.backStackEntryCount > 1) {
//                (activity as MainActivity).finish()

//                fragmentManager.popBackStack()
//    }

        //            val fragmentManager = (activity as MainActivity).supportFragmentManager
//            (activity as MainActivity).finish()
//            fragmentManager.findFragmentById(R.id.frame_container)
//            if (fragmentManager.backStackEntryCount > 0) {
//                fragmentManager.popBackStack()
//            }
//            true
//        } else
//            false
//        val currentFragment = activity?.supportFragmentManager?.findFragmentByTag(TAG)
//        if (currentFragment != null && currentFragment.onOptionsItemSelected(item)) {
//            return true
//        }
        val id = item.itemId
        if (id == android.R.id.home) {
//            (activity as MainActivity).finish()
            (activity as MainActivity).onBackPressed()
//            (activity as MainActivity).supportFragmentManager.popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
