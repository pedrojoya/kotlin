package es.iessaladillo.pedrojoya.pr059.ui.main


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr059.R
import es.iessaladillo.pedrojoya.pr059.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr059.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr059.data.local.Database
import es.iessaladillo.pedrojoya.pr059.extensions.toast
import es.iessaladillo.pedrojoya.pr059.extensions.viewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModelProvider {
        MainFragmentViewModel(RepositoryImpl(Database))
    }
    private val listAdapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter().apply {
            setOnItemClickListener { _, position -> showStudent(getItem(position)) }
        }
    }
    private var searchView: SearchView? = null
    private var mnuSearch: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeStudents()
    }

    private fun setupViews() {
        setupRecyclerView()
    }

    private fun observeStudents() {
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            listAdapter.submitList(students)
            lblEmptyView.visibility = if (students.isNotEmpty()) View.INVISIBLE else View.VISIBLE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_main, menu)
        mnuSearch = menu.findItem(R.id.mnuSearch)
        mnuSearch?.run {
            searchView = actionView as SearchView
            setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                    viewModel.isSearchViewExpanded = true
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                    viewModel.isSearchViewExpanded = false
                    return true
                }
            })

        }
        searchView?.run {
            maxWidth = Integer.MAX_VALUE
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String): Boolean {
                    // filter adapter when text is changed
                    // listAdapter.getFilter().filter(query);
                    viewModel.searchQuery = query
                    return false
                }
            })
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        // Restore searching state (in this order).
        val query = viewModel.searchQuery
        if (viewModel.isSearchViewExpanded) {
            // If done directly, menu item disappears after collapsing.
            lstStudents.post {
                mnuSearch?.expandActionView()
                if (!TextUtils.isEmpty(query)) {
                    searchView?.setQuery(query, false)
                }
            }
        }

    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun showStudent(student: String) {
        requireContext().toast(getString(R.string.main_student_clicked, student))
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }

    }

}
