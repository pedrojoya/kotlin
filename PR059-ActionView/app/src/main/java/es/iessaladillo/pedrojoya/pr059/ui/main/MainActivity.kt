package es.iessaladillo.pedrojoya.pr059.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr059.R
import es.iessaladillo.pedrojoya.pr059.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr059.extensions.onQueryTextChange
import es.iessaladillo.pedrojoya.pr059.extensions.setOnActionExpandListener
import es.iessaladillo.pedrojoya.pr059.extensions.toast
import es.iessaladillo.pedrojoya.pr059.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider()
    private val listAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter().apply {
            emptyView = lblEmptyView
            setOnItemClickListener { _, position ->
                showStudent(getItem(position))
            }
        }
    }
    private var mnuSearch: MenuItem? = null
    private var searchView: SearchView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        listAdapter.submitList(viewModel.students)
    }

    private fun initViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            adapter = listAdapter
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
                listAdapter.filter.filter(it)
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
