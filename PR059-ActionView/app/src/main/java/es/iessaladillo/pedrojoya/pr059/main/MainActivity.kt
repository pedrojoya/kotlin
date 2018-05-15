package es.iessaladillo.pedrojoya.pr059.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr059.R
import es.iessaladillo.pedrojoya.pr059.extensions.getStringArray
import es.iessaladillo.pedrojoya.pr059.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr059.extensions.onQueryTextChange
import es.iessaladillo.pedrojoya.pr059.extensions.setOnActionExpandListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var mAdapter: ArrayAdapter<String>
    private var mnuSearch: MenuItem? = null
    private var searchView: SearchView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel()
        initViews()
    }

    private fun initViews() {
        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getStringArray(R.array.students))
        with(lstStudents) {
            adapter = mAdapter
            setOnItemClickListener { _, _, position, _ ->
                showStudent(getItemAtPosition(position) as String)
            }
        }
    }

    private fun showStudent(student: String) {
        toast(getString(R.string.main_activity_student_clicked, student))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        setupSearchView(menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupSearchView(menu: Menu) {
        mnuSearch = menu.findItem(R.id.mnuSearch).apply {
            setOnActionExpandListener(
                    { viewModel.isSearchViewExpanded = true; true },
                    { viewModel.isSearchViewExpanded = false; true })
        }
        searchView = (mnuSearch!!.actionView as SearchView).apply {
            maxWidth = Integer.MAX_VALUE
            onQueryTextChange {
                mAdapter.filter.filter(it)
                viewModel.searchQuery = it
                false
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        // Restore searching state (in this order).
        val query = viewModel.searchQuery
        if (viewModel.isSearchViewExpanded) {
            // If done directly, menu item disappears after collapsing.
            lstStudents.post {
                mnuSearch?.expandActionView()
                if (query.isNotBlank()) {
                    searchView?.setQuery(query, false)
                }
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

}
