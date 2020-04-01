package com.eburg_soft.contactsapp.presentation.screen.contact_list

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.base.BaseAdapter
import com.eburg_soft.contactsapp.presentation.base.BaseListFragment
import com.eburg_soft.contactsapp.presentation.screen.contact.ContactFragment
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsAdapter.OnContactItemClickListener
import com.eburg_soft.contactsapp.presentation.screen.main.MainActivity
import com.eburg_soft.contactsapp.utils.MyNetworkUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.progressbar
import kotlinx.android.synthetic.main.fragment_contacts_list.swipe_refresh_layout
import javax.inject.Inject

/**
 * Class [ContactsListFragment] show the list of contacts and assist to find contacts by phone number
 * or name.
 */
class ContactsListFragment : BaseListFragment(R.layout.fragment_contacts_list),
    SwipeRefreshLayout.OnRefreshListener,
    SearchView.OnQueryTextListener,
    OnContactItemClickListener, ContactsListContract.View {

    private val BUNDLE_SEARCH_QUERY: String = "searchQuery"
    private val BUNDLE_CONTACTS_LIST: String = "contactsList"
    private val BUNDLE_RECYCLER_VIEW_POSITION = "recyclerPosition"
    private var searchQuery: String? = ""

    @BindView(R.id.action_search)
    lateinit var searchView: SearchView

    @Inject
    lateinit var presenter: ContactsListContract.Presenter

    private lateinit var listAdapter: ContactsAdapter

    private var contactsList: ArrayList<Contact>? = null

    companion object {
        const val TAG = "ContactsListFragment"
    }

//region ====================== Life circle ======================

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view: View = inflater.inflate(R.layout.fragment_contacts_list, container, false)
//        return view
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        swipe_refresh_layout.setOnRefreshListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getScreenComponent(requireContext()).inject(this)
        presenter.attach(this)
        setHasOptionsMenu(true)

        retainInstance = true

        listAdapter = ContactsAdapter(this)


        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(BUNDLE_SEARCH_QUERY).toString()
            contactsList = savedInstanceState.getParcelableArrayList(BUNDLE_CONTACTS_LIST)

            val listState: Parcelable = savedInstanceState.getParcelable(BUNDLE_RECYCLER_VIEW_POSITION)!!
            recyclerView.layoutManager?.onRestoreInstanceState(listState)
        }

        if (MyNetworkUtils.isNetworkAvailable(requireContext())) {
            presenter.syncContacts()
        }

        presenter.loadContactsList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.activity_main_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = MenuItemCompat.getActionView(searchItem) as SearchView
//        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.maxWidth = Int.MAX_VALUE
        searchItem.expandActionView()
        searchView.requestFocus()

        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (!TextUtils.isEmpty(searchQuery) && !TextUtils.equals(searchQuery, "")) {
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
        presenter.eraseContactsFromDB()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_SEARCH_QUERY, searchQuery)
        outState.putParcelableArrayList(BUNDLE_CONTACTS_LIST, contactsList)
    }

//endregion

    //refresh the list by swiping down
    override fun onRefresh() {
        viewAdapter.items.clear()
        presenter.refreshContactsList()
        swipe_refresh_layout.isRefreshing = false
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchQuery = query
        val networkAvailable: Boolean = MyNetworkUtils.isNetworkAvailable(requireContext())
        presenter.onSearchQuerySubmit(query, networkAvailable)
        Log.d("onQueryTextSubmit", query)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        searchQuery = newText
        val networkAvailable: Boolean = MyNetworkUtils.isNetworkAvailable(requireContext())
        presenter.onSearchQuerySubmit(newText, networkAvailable)
        Log.d("onQueryTextChange", newText)
        return false
    }

    override fun onContactClick(contact: Contact) {
        presenter.onContactClick(contact)
        Log.d("onContactClick", contact.contactName)
    }

    //region ====================== Contract ======================

    override fun createAdapterInstance(): BaseAdapter<*> {
        return ContactsAdapter()
    }

    override fun addContact(contact: Contact) {
        viewAdapter.add(contact)
    }

    override fun showContactsList() {
        presenter.loadContactsList()
    }

    override fun showLoading() {
        requireActivity().progressbar.visibility = View.VISIBLE
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun hideLoading() {
        requireActivity().progressbar.visibility = View.INVISIBLE
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
