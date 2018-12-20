package es.iessaladillo.pedrojoya.pr092.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr092.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels { MainFragmentViewModelFactory() }
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
        initViews()
        // We shouldn't do like this. We should define a state of the request.
        if (savedInstanceState == null) {
            swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = true }
        }
        viewModel.data.observe(viewLifecycleOwner, Observer { data ->
            listAdapter.submitList(data)
            swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun initViews() {
        setupPanel()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        listAdapter.emptyView = lblEmptyView
        lstList.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL,
                    false)
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
