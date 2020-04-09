package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.screen.contact.ContactFragment
import com.eburg_soft.contactsapp.presentation.screen.contact_list.adapter.ContactsListAdapter
import com.eburg_soft.contactsapp.presentation.screen.contact_list.workmanager.SyncWorker
import com.eburg_soft.contactsapp.presentation.screen.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.progressbar
import kotlinx.android.synthetic.main.fragment_contacts_list.recycler_contacts
import kotlinx.android.synthetic.main.fragment_contacts_list.swipe_refresh_layout
import javax.inject.Inject

/**
 * Class [ContactsListFragment] show the list of contacts and assist to find contacts by phone number
 * or name.
 */
class ContactsListFragment :
    Fragment(R.layout.fragment_contacts_list),
    SwipeRefreshLayout.OnRefreshListener,
    SearchView.OnQueryTextListener,
    ContactsListAdapter.OnContactItemClickListener,
    ContactsListContract.View {

    private val BUNDLE_SEARCH_QUERY = "search query"
    private var searchQuery: String? = ""

    @Inject
    lateinit var presenter: ContactsListContract.Presenter

    private val listAdapterList = ContactsListAdapter(this)

    private var lastSyncTime: Long = 0L

    private var isFirstTime: Boolean = true

    companion object {
        const val TAG = "ContactsListFragment"
        const val MINUTE: Long = 60000L
    }

    //region ====================== Life circle ======================

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_contacts.adapter = listAdapterList
        recycler_contacts.layoutManager = LinearLayoutManager(context)
        recycler_contacts.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        swipe_refresh_layout.setOnRefreshListener(this)
        setHomeButtonInvisible()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getScreenComponent(requireContext()).inject(this)
        setHasOptionsMenu(true)

        retainInstance = true

        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(BUNDLE_SEARCH_QUERY).toString()
        }
        else {
            presenter.loadContactsListFromDB()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.activity_main_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = MenuItemCompat.getActionView(searchItem) as SearchView

        searchView.maxWidth = Int.MAX_VALUE
        searchItem.expandActionView()
        searchView.requestFocus()

        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchQuery!!.isNotEmpty()) {
            searchView.setQuery(searchQuery, false)
        }

        searchView.setOnQueryTextListener(this)
        searchView.setSearchableInfo(searchManager.getSearchableInfo((activity as MainActivity).componentName))
        searchView.setIconifiedByDefault(false)
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveVariables()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_SEARCH_QUERY, searchQuery)
    }

    //endregion

    //region ====================== Overrided methods ======================

    //refresh the list by swiping down
    override fun onRefresh() {
        presenter.loadContactsListFromDB()
        swipe_refresh_layout.isRefreshing = false
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchQuery = query

        val copyContactsList = ArrayList<Contact>(listAdapterList.currentList.toList())
        presenter.onSearchQuerySubmit(query, copyContactsList)

        Log.d("onQueryTextSubmit", query)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        searchQuery = newText

        val copyContactsList = ArrayList<Contact>(listAdapterList.currentList.toList())
        presenter.onSearchQuerySubmit(newText, copyContactsList)

        Log.d("onQueryTextChange", newText)
        return false
    }

    override fun onContactClick(contact: Contact) {
        presenter.onContactClick(contact)
        Log.d("onContactClick", contact.contactName)
    }

    //endregion

    //region ====================== Contract ======================

    override fun showLoading() {
        requireActivity().progressbar.visibility = View.VISIBLE
    }

    override fun submitList(list: List<Contact>) {
        listAdapterList.submitList(list)
    }

    override fun showNetworkErrorMessage() {
        Snackbar.make(recycler_contacts, "Нет подключения к сети", Snackbar.LENGTH_LONG).show()
    }

    override fun hideLoading() {
        requireActivity().progressbar.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String) {
        Snackbar.make(recycler_contacts, error, Snackbar.LENGTH_LONG).show()
    }

    //Open [ContactsFragment] with data of the contact
    override fun openContactView(contact: Contact) {
        requireActivity().supportFragmentManager.let {
            if (it.findFragmentByTag(ContactFragment.TAG) == null) {
                it.beginTransaction()
                    .replace(R.id.frame_container, ContactFragment.NewInstance(contact), ContactFragment.TAG)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    //endregion

    private fun saveVariables() {
        isFirstTime = false

        this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)!!.edit()
            .putBoolean("isFirstTime", isFirstTime)
            .apply()
        this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)!!.edit()
            .putLong("lastSyncTime", lastSyncTime)
            .apply()
    }

    private fun loadVariables() {
        isFirstTime =
            this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)!!.getBoolean("isFirstTime", true)

        lastSyncTime =
            this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)!!.getLong("lastSyncTime", 0L)
    }

    override fun setWorkManager() {
        loadVariables()

        val currentTime = System.currentTimeMillis()
        val timeDifference = currentTime - lastSyncTime

        if ((!isFirstTime && (timeDifference > MINUTE)) || isFirstTime) {

            val workRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java)
                .build()

            WorkManager.getInstance().enqueue(workRequest)

            WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.id)
                .observe(this, Observer<WorkInfo> {
                    presenter.syncContacts()
//                    presenter.loadContactsListFromDB()
                    lastSyncTime = System.currentTimeMillis()
                })
        }
    }

    private fun setHomeButtonInvisible() {
        val actionBar: ActionBar? = (activity as MainActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)
    }
}
