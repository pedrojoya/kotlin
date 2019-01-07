package es.iessaladillo.pedrojoya.pr040.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import es.iessaladillo.pedrojoya.pr040.R
import es.iessaladillo.pedrojoya.pr040.base.Event
import es.iessaladillo.pedrojoya.pr040.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr040.data.model.Student
import es.iessaladillo.pedrojoya.pr040.data.remote.ApiServiceImpl
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment() {

    private val listAdapter: MainFragmentAdapter by lazy { MainFragmentAdapter() }
    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(RepositoryImpl(ApiServiceImpl()))
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
        setupPanel()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), requireContext().resources
                    .getInteger(R.integer.main_lstStudents_columns))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun setupPanel() {
        swlPanel.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        swlPanel.setOnRefreshListener { viewModel.refreshStudents() }
    }

    private fun observeStudents() {
        viewModel.students.observe(viewLifecycleOwner, Observer { resource ->
            when {
                resource.isLoading -> swlPanel.post { swlPanel.isRefreshing = true }
                resource.hasError() -> showErrorLoadingStudents(resource.exception!!)
                resource.hasSuccess() -> showStudents(resource.data!!)
            }
        })
    }

    private fun showStudents(students: List<Student>) {
        listAdapter.submitList(students)
        lblEmptyView.visibility = if (students.isEmpty()) View.VISIBLE else View.INVISIBLE
        swlPanel.post { swlPanel.isRefreshing = false }
    }

    private fun showErrorLoadingStudents(event: Event<Exception>) {
        val exception = event.getContentIfNotHandled()
        if (exception != null) {
            Toast.makeText(requireContext(),exception.message?:getString(R.string
                    .main_error_loading_students), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuRefresh) {
            viewModel.refreshStudents()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}