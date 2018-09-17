package es.iessaladillo.pedrojoya.pr132.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr132.R
import es.iessaladillo.pedrojoya.pr132.extensions.getStringArray
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val listAdapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter().apply {
            submitList(arrayListOf(*getStringArray(R.array.students)))
            emptyView = lblEmpty
        }
    }
    private var shareActionProvider: ShareActionProvider? = null

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
    }

    private fun initViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,
                    false)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.activity_main, menu)
        shareActionProvider = (MenuItemCompat.getActionProvider(
                menu!!.findItem(R.id.mnuShare)) as ShareActionProvider).apply {
            setShareIntent(createShareIntent())
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun createShareIntent() = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, getStringArray(R.array.students).joinToString("\n"))
    }

}
