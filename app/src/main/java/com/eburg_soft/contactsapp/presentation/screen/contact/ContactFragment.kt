package com.eburg_soft.contactsapp.presentation.screen.contact

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.screen.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.progressbar
import kotlinx.android.synthetic.main.fragment_contact.text_biography_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_education_period_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_name_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_phone_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_temperament_in_contact

/**
 *  ContactFragment shows contact information, has clickable phone number, back button.
 */
class ContactFragment : Fragment(R.layout.fragment_contact) {

    private var contact: Contact? = null

    companion object {
        const val TAG = "ContactFragment"
        const val REQUEST_CODE_PERMISSION_CALL_PHONE: Int = 0
        private const val CONTACT = "Contact"

        @JvmStatic
        fun getNewInstance(contact: Contact) =
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

        contact = requireArguments().getParcelable(CONTACT) as Contact?
    }

    override fun onStart() {
        super.onStart()
        setupToolbar()
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
        text_temperament_in_contact.text = contact?.contactTemperament?.type
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
}
