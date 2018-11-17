package es.iessaladillo.pedrojoya.pr249.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr249.R
import es.iessaladillo.pedrojoya.pr249.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr249.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr249.data.local.Database
import es.iessaladillo.pedrojoya.pr249.extensions.viewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*

class ListFragment : Fragment() {

    private lateinit var listener: ListFragment.OnItemSelectedListener
    private val listAdapter: ListFragmentAdapter by lazy { ListFragmentAdapter() }
    private val viewModel: ListFragmentViewModel by viewModelProvider {
        ListFragmentViewModel(RepositoryImpl(Database))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        try {
            listener = activity as OnItemSelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                    activity.toString() + " must implement ListFragment.OnItemSelectedListener")
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(view!!)
        refreshData()
    }

    private fun refreshData() {
        val students = viewModel.getStudents(false)
        listAdapter.submitList(students)
        lblEmptyView.visibility = if (students.isNotEmpty()) View.INVISIBLE else View.VISIBLE
    }

    private fun setupViews(view: View) {
        setupToolbar()
        setupRecyclerView(view)
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.list_title)
        }
    }

    private fun setupRecyclerView(view: View) {
        listAdapter.setOnItemClickListener { _, position ->
            listener.onItemSelected(listAdapter.getItem(position))
        }
        lstItems.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: String)
    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }

    }

}
