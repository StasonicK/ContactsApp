package com.eburg_soft.contactsapp.presentation.screen

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.R.layout
import com.eburg_soft.contactsapp.presentation.screen.contact.ContactFragment
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListFragment
import com.eburg_soft.contactsapp.utils.MyNetworkUtils

class MainActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener {

    @BindView(R.id.toolbar_main)
    lateinit var toolbar: Toolbar

    @BindView(R.id.action_search)
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
//        mPresenter.onSearchQuerySubmit(query, networkAvailable)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val networkAvailable: Boolean = MyNetworkUtils.isNetworkAvailable(this)
//        mPresenter.onSearchQuerySubmit(newText, networkAvailable)
        return true
    }
}
