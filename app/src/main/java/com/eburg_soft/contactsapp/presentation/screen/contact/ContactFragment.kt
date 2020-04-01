package com.eburg_soft.contactsapp.presentation.screen.contact

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseFragment
import com.eburg_soft.contactsapp.presentation.screen.main.MainActivity
import kotlinx.android.synthetic.main.fragment_contact.text_biography_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_education_period_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_name_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_phone_in_contact
import kotlinx.android.synthetic.main.fragment_contact.text_temperament_in_contact
import kotlinx.android.synthetic.main.toolbar.toolbar
import javax.inject.Inject

/**
 * Class [ContactFragment] shows contact information, has clickable phone number.
 */
class ContactFragment @Inject constructor() : BaseFragment(R.layout.fragment_contact), ContactContract.View {

    @Inject
    lateinit var presenter: ContactContract.Presenter

    private var myCondition = true

    var contact: Contact = Contact()

    init {
        getScreenComponent(requireContext()).inject(this)
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)

        retainInstance = true
        myCondition = true

        setupToolbar()

        val contact = requireArguments().getParcelable(CONTACT) as Contact?
        savedInstanceState?.let { state ->
            (contact as Contact).contactName = state.getString(BUNDLE_CONTACT_NAME, contact.contactName).toString()
            contact.contactPhone = state.getString(BUNDLE_CONTACT_PHONE, contact.contactPhone).toString()
            contact.contactTemperament =
                state.getString(BUNDLE_CONTACT_TEMPERAMENT, contact.contactTemperament).toString()
            contact.contactEducationStart =
                state.getString(BUNDLE_CONTACT_EDUCATION_START, contact.contactEducationStart).toString()
            contact.contactEducationEnd =
                state.getString(BUNDLE_CONTACT_EDUCATION_END, contact.contactEducationEnd).toString()
            contact.contactBiography =
                state.getString(BUNDLE_CONTACT_BIOGRAPHY, contact.contactBiography).toString()
        }

        bindViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_CONTACT_NAME, contact.contactName)
        outState.putString(BUNDLE_CONTACT_PHONE, contact.contactPhone)
        outState.putString(BUNDLE_CONTACT_TEMPERAMENT, contact.contactTemperament)
        outState.putString(BUNDLE_CONTACT_EDUCATION_START, contact.contactEducationStart)
        outState.putString(BUNDLE_CONTACT_EDUCATION_END, contact.contactEducationEnd)
        outState.putString(BUNDLE_CONTACT_BIOGRAPHY, contact.contactBiography)
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    //endregion

    @OnClick(R.id.text_phone_in_contact)
    override fun callPhone() {
        val phone = text_phone_in_contact.text
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel: $phone")
        startActivity(intent)
    }

    override fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).actionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).actionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { v -> onBackPressed() }
    }

    override fun bindViews() {
        text_name_in_contact.text = contact.contactName
        text_phone_in_contact.text = contact.contactPhone
        text_temperament_in_contact.text = contact.contactTemperament
        val education = "${contact.contactEducationStart} - ${contact.contactEducationEnd}"
        text_education_period_in_contact.text = education
        text_biography_in_contact.text = contact.contactBiography
    }

    override fun onBackPressed(): Boolean {
        return if (myCondition) {
            myCondition = false
            true
        } else {
            false
        }
    }
}
