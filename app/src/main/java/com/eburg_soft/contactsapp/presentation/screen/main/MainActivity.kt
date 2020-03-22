package com.eburg_soft.contactsapp.presentation.screen.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.R.layout
import com.eburg_soft.contactsapp.model.source.database.entity.Contact
import com.eburg_soft.contactsapp.presentation.screen.contact.ContactFragment
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListFragment
import com.eburg_soft.contactsapp.utils.MyNetworkUtils
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener, MainContract.View {

    @BindView(R.id.toolbar_main)
    lateinit var toolbar: Toolbar

    @BindView(R.id.progressbar)
    lateinit var progressbar: ProgressBar

    @Inject
    lateinit var presenter: MainContract.Presenter

    lateinit var searchView: SearchView
    lateinit var contactsListFragment: ContactsListFragment
    lateinit var contactFragment: ContactFragment

    private val BUNDLE_SEARCH_QUERY: String = "searchQuery"
    private var searchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        if (contactsListFragment == null && savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.frame_container, ContactsListFragment())
                .commit()
        }

        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(BUNDLE_SEARCH_QUERY).toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.isSubmitButtonEnabled = true

        return true
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(BUNDLE_SEARCH_QUERY, searchQuery)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val networkAvailable: Boolean = MyNetworkUtils.isNetworkAvailable(this)
        presenter.onSearchQuerySubmit(query, networkAvailable)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val networkAvailable: Boolean = MyNetworkUtils.isNetworkAvailable(this)
        presenter.onSearchQuerySubmit(newText, networkAvailable)
        return true
    }

    override fun showContactsList(contacts: List<Contact?>?) {
        presenter.loadContactsList()
    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.INVISIBLE
    }

    override fun showNetworkError() {
        TODO("Not yet implemented")
    }

    override fun openContactView(contact: Contact?) {
        TODO("Not yet implemented")
    }
}
