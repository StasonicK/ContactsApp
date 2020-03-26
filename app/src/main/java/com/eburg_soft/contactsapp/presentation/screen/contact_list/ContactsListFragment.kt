package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseAdapter
import com.eburg_soft.contactsapp.presentation.base.BaseListFragment
import com.eburg_soft.contactsapp.presentation.screen.contact.ContactFragment
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsAdapter.OnContactItemClickListener
import com.eburg_soft.contactsapp.presentation.screen.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * Class [ContactsListFragment] show the list of contacts and assist to find contacts by phone number
 * or name.
 */

class ContactsListFragment : BaseListFragment(R.layout.fragment_contacts_list), SwipeRefreshLayout.OnRefreshListener,
    ContactsAdapter.OnContactItemClickListener, ContactsListContract.View {

    private val BUNDLE_SEARCH_QUERY: String = "searchQuery"
    private var searchQuery: String = ""

    @BindView(R.id.progressbar)
    lateinit var progressbar: ProgressBar

    @BindView(R.id.action_search)
    lateinit var searchView: SearchView

    @Inject
    lateinit var presenter: ContactsListContract.Presenter

    private lateinit var listener: OnContactItemClickListener

    private val listAdapter = ContactsAdapter(this)

    companion object {
        const val TAG = "ContactsListFragment"

        @JvmStatic
        fun NewInstance() =
            ContactsListFragment()
    }

//region ====================== Life circle ======================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(BUNDLE_SEARCH_QUERY).toString()
        }
        getScreenComponent(requireContext()).inject(this)
        presenter.loadContactsList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.attach(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.activity_main_menu, menu)
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

//        val search = menu.findItem(R.id.action_search)
//        search.expandActionView()
        searchView.setQuery(searchQuery, false)
        searchView.setSearchableInfo(searchManager.getSearchableInfo((activity as MainActivity).componentName))
        searchView.setIconifiedByDefault(false)
        searchView.isSubmitButtonEnabled = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_SEARCH_QUERY, searchQuery)
    }

//endregion

    //refresh the list by swiping down
    override fun onRefresh() {
        presenter.refreshContactsList()
    }

//region ====================== Contract ======================

    override fun createAdapterInstance(): BaseAdapter<*> {
        return ContactsAdapter()
    }

    override fun onContactsListItemClick(contact: Contact) {
        listener.onContactsListItemClick(contact)
        presenter.onContactClick(contact)
    }

    override fun addCurrency(contact: Contact) {
        viewAdapter.add(contact)
    }

    override fun showContactsList() {
        presenter.loadContactsList()
    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun hideLoading() {
        progressbar.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Snackbar.make(recyclerView, error.toString(), Snackbar.LENGTH_LONG).show()
    }

    //Open [ContactsFragment] with data of the contact
    override fun openContactView(contact: Contact) {
        (activity as MainActivity).supportFragmentManager.let {
            if (it.findFragmentByTag(ContactFragment.TAG) == null) {
                it.beginTransaction()
                    .replace(R.id.frame_container, ContactFragment.NewInstance(contact), ContactFragment.TAG)
                    .commit()
            }
        }
    }

//endregion

    
    //TODO create WorkManager!
}
