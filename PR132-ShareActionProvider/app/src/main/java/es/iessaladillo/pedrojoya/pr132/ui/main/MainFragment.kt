package es.iessaladillo.pedrojoya.pr132.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr132.R
import es.iessaladillo.pedrojoya.pr132.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr132.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr132.data.local.Database
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(RepositoryImpl(Database))
    }
    private val listAdapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter().apply {
            setOnItemClickListener { _, position -> deleteStudent(getItem(position)) }
        }
    }
    private val shareIntent = Intent(Intent.ACTION_SEND).setType("text/plain")

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

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun observeStudents() {
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            listAdapter.submitList(students)
            lblEmptyView.visibility = if (students.isNotEmpty()) View.INVISIBLE else View.VISIBLE
            shareIntent.putExtra(Intent.EXTRA_TEXT, students.joinToString(", "))
        })
    }

    private fun deleteStudent(student: String) {
        viewModel.deleteStudent(student)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_main, menu)
        val shareActionProvider = MenuItemCompat.getActionProvider(
                menu.findItem(R.id.mnuShare)) as ShareActionProvider
        shareActionProvider.setShareIntent(shareIntent)
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
