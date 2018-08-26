package es.iessaladillo.pedrojoya.pr092.main

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.pr092.R
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*

private const val SIMULATION_SLEEP_MILI: Long = 2000

class MainFragment : Fragment() {

    private val mSimpleDateFormat = SimpleDateFormat("HH:mm:ss",
            Locale.getDefault())
    private lateinit var mAdapter: MainAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
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
        mAdapter = MainAdapter(viewModel.data)
        mAdapter.emptyView = emptyView
        lstList.run {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,
                    false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
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
        mAdapter.addItem(mSimpleDateFormat.format(Date()))
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

}
