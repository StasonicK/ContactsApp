package com.eburg_soft.contactsapp.presentation.screen.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import com.eburg_soft.contactsapp.R
import com.eburg_soft.contactsapp.R.layout
import com.eburg_soft.contactsapp.presentation.screen.contact.ContactFragment
import com.eburg_soft.contactsapp.presentation.screen.contact_list.ContactsListFragment

class MainActivity :
//    SearchView.OnQueryTextListener,
    AppCompatActivity()
//    , MainContract.View
{

    private val BUNDLE_SEARCH_QUERY: String = "searchQuery"
    private var searchQuery: String = ""

//    @Inject
//    lateinit var presenter:MainContract.Presenter

//    @BindView(R.id.toolbar)    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        ButterKnife.bind(this)

//        setSupportActionBar(toolbar)

//        supportFragmentManager.let {
//            if (it.findFragmentByTag(ContactsListFragment.TAG) == null) {
//                it.beginTransaction()
//                    .add(R.id.frame_container, ContactsListFragment.NewInstance())
//                    .addToBackStack("ContactsListFragment")
//                    .commit()
//            }
//        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.frame_container,
                    ContactsListFragment(), ContactsListFragment.TAG + " created"
                )
                .commit()
        } else {
            searchQuery = savedInstanceState.getString(BUNDLE_SEARCH_QUERY).toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(BUNDLE_SEARCH_QUERY, searchQuery)
    }

    //    override fun onQueryTextSubmit(query: String?): Boolean {
//        val networkAvailable: Boolean = MyNetworkUtils.isNetworkAvailable(this)
//        presenter.loadContactsListByQuery(query, networkAvailable)
//        return false
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        val networkAvailable: Boolean = MyNetworkUtils.isNetworkAvailable(this)
//        presenter.loadContactsListByQuery(newText, networkAvailable)
//        return false
//    }

//    override fun showLoading() {
//        TODO("Not yet implemented")
//    }
//
//    override fun hideLoading() {
//        TODO("Not yet implemented")
//    }
//
//    override fun notifyAdapter() {
//        TODO("Not yet implemented")
//    }
//
//    override fun showErrorMessage(error: String?) {
//        TODO("Not yet implemented")
//    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.activity_main_menu, menu)
//        val searchManager = baseContext.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.setIconifiedByDefault(false)
//        searchView.isSubmitButtonEnabled = true
//
//        return true
//    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.frame_container)
        (fragment as? ContactFragment)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }
}
