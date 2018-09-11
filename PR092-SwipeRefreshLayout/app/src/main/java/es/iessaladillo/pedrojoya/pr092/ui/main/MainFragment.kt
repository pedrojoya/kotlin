package es.iessaladillo.pedrojoya.pr092.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr092.R
import es.iessaladillo.pedrojoya.pr092.extensions.viewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*

private const val SIMULATION_SLEEP_MILI: Long = 2000

class MainFragment : Fragment() {

    private val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    private val viewModel: MainFragmentViewModel by viewModelProvider()
    private val listAdapter: MainAdapter by lazy { MainAdapter(viewModel.data) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initViews()
        // Initial data load. With post so the animation works properly.
        if (savedInstanceState == null) {
            swipeRefreshLayout.post {
                swipeRefreshLayout.isRefreshing = true
                refresh()
            }
        }
        super.onActivityCreated(savedInstanceState)
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
            setOnRefreshListener { refresh() }
        }
    }

    private fun refresh() {
        // Loading time simulation.
        Handler().postDelayed({ addData() }, SIMULATION_SLEEP_MILI)
    }

    private fun addData() {
        listAdapter.addItem(simpleDateFormat.format(Date()))
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.activity_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                R.id.mnuRefresh -> {
                    swipeRefreshLayout.isRefreshing = true
                    refresh()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    companion object {

        fun newInstance() = MainFragment()

    }
}
