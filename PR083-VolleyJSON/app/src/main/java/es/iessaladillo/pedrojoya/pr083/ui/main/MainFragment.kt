package es.iessaladillo.pedrojoya.pr083.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr083.R
import es.iessaladillo.pedrojoya.pr083.base.Event
import es.iessaladillo.pedrojoya.pr083.base.RequestState
import es.iessaladillo.pedrojoya.pr083.data.model.Student
import es.iessaladillo.pedrojoya.pr083.data.remote.VolleyInstance
import es.iessaladillo.pedrojoya.pr083.extensions.toast
import es.iessaladillo.pedrojoya.pr083.extensions.viewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment() {

    private val listAdapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter().apply {
            emptyView = lblEmptyView
        }
    }
    private val viewModel: MainFragmentViewModel by viewModelProvider {
        MainFragmentViewModel(VolleyInstance.getInstance(requireContext()).requestQueue)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        viewModel.students.observe(viewLifecycleOwner, Observer { request ->
            @Suppress("UNCHECKED_CAST")
            when (request) {
                is RequestState.Loading -> swlPanel.post { swlPanel.isRefreshing = request.isLoading }
                is RequestState.Error -> showErrorLoadingStudents(request.exception)
                is RequestState.Result<*> -> showStudents((request as RequestState.Result<List<Student>>).data)
            }
        })

    }

    private fun initViews() {
        setupPanel()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL,
                    false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun setupPanel() {
        swlPanel.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
        swlPanel.setOnRefreshListener { viewModel.forceLoadStudents() }
    }

    private fun showStudents(students: List<Student>) {
        listAdapter.submitList(students)
        swlPanel.post { swlPanel.isRefreshing = false }
    }

    private fun showErrorLoadingStudents(event: Event<Exception>) {
        val exception = event.getContentIfNotHandled()
        if (exception != null) {
            toast(exception.message?:getString(R.string.main_fragment_error_loading_students))
        }
    }

}
