package es.iessaladillo.pedrojoya.pr178.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import es.iessaladillo.pedrojoya.pr178.R
import es.iessaladillo.pedrojoya.pr178.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr178.data.local.Database
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(RepositoryImpl(Database))
    }
    private val listAdapter: MainFragmentAdapter by lazy { MainFragmentAdapter() }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            listAdapter.submitList(students)
            lblEmptyView.visibility = if (students.isEmpty()) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun setupViews() {
        setupToolbar()
        setupRecyclerView()
        setupFab()
    }

    private fun setupFab() {
        fab.setOnClickListener { viewModel.addStudent(Database.newFakeStudent()) }
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                    DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            val itemTouchHelper = ItemTouchHelper(
                    object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

                        override fun onMove(recyclerView: RecyclerView,
                                            viewHolder: RecyclerView.ViewHolder,
                                            target: RecyclerView.ViewHolder): Boolean = false

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder,
                                              direction: Int) {
                            viewModel.deleteStudent(listAdapter.getItem(viewHolder.adapterPosition))
                        }
                    })
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
