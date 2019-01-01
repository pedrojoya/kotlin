package es.iessaladillo.pedrojoya.pr092.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr092.R
import es.iessaladillo.pedrojoya.pr092.base.RequestState
import es.iessaladillo.pedrojoya.pr092.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr092.data.local.Database
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(RepositoryImpl(Database))
    }
    private val listAdapter: MainFragmentAdapter = MainFragmentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeData()
        observeRefreshState()
        if (savedInstanceState == null) {
            swipeRefreshLayout.post { viewModel.refresh() }
        }
    }

    private fun setupViews() {
        setupPanel()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstList.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun setupPanel() {
        swipeRefreshLayout.run {
            setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light, android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
            setOnRefreshListener { viewModel.refresh() }
        }
    }

    private fun observeRefreshState() {
        viewModel.refreshState.observe(viewLifecycleOwner, Observer { requestState ->
            when (requestState) {
                is RequestState.Loading -> swipeRefreshLayout.isRefreshing = true
                is RequestState.Error -> {
                    showError(requestState)
                    swipeRefreshLayout.isRefreshing = false
                }
                else -> swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun observeData() {
        viewModel.data.observe(viewLifecycleOwner, Observer { items ->
            listAdapter.submitList(items)
            lblEmptyView.visibility = if (items.isEmpty()) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun showError(requestState: RequestState.Error) {
        val exceptionEvent = requestState.exception
        val exception = exceptionEvent.getContentIfNotHandled()
        if (exception != null) {
            Snackbar.make(lblEmptyView, exception.message!!, Snackbar.LENGTH_SHORT).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.activity_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.mnuRefresh -> {
                    swipeRefreshLayout.isRefreshing = true
                    viewModel.refresh()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    companion object {

        fun newInstance() = MainFragment()

    }

}
