package es.iessaladillo.pedrojoya.pr086.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr086.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr086.data.local.Database
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import es.iessaladillo.pedrojoya.pr086.extensions.toast
import es.iessaladillo.pedrojoya.pr086.extensions.viewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val listAdapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter().apply {
            setOnItemClickListener { v, position -> showStudent(getItem(position)) }
            setOnShowAssignmentsListener { position -> showAssignments(getItem(position)) }
            setOnShowMarksListener { position -> showMarks(getItem(position)) }
            setOnCallListener { position -> call(getItem(position)) }
            setOnSendMessageListener { position -> sendMessage(getItem(position)) }
        }
    }
    private val viewModel: MainFragmentViewModel by viewModelProvider {
        MainFragmentViewModel(RepositoryImpl(Database))
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            listAdapter.submitList(students)
            lblEmptyView.visibility = if (students.size > 0) View.INVISIBLE else View.VISIBLE
        })
    }

    private fun setupViews() {
        setupListView()
        setupFab()
    }

    private fun setupListView() {
        lblEmptyView.setOnClickListener { v -> viewModel.addStudent(Database.newFakeStudent()) }
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(),
                    resources.getInteger(R.integer.main_lstStudents_columns))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            ItemTouchHelper(
                    object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

                        override fun onMove(recyclerView: RecyclerView,
                                            viewHolder: RecyclerView.ViewHolder,
                                            target: RecyclerView.ViewHolder): Boolean {
                            return false
                        }

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            viewModel.deleteStudent(listAdapter.getItem(viewHolder.adapterPosition))
                        }
                    }).attachToRecyclerView(this)
        }
    }

    private fun setupFab() {
        fab.setOnClickListener { viewModel.addStudent(Database.newFakeStudent()) }
    }

    private fun showStudent(student: Student) {
        toast(getString(R.string.main_activity_click_on, student.name))
    }

    private fun showAssignments(student: Student) {
        toast(getString(R.string.main_show_assignments, student.name))
    }

    private fun showMarks(student: Student) {
        toast(getString(R.string.main_show_marks, student.name))
    }

    private fun call(student: Student) {
        toast(getString(R.string.main_activity_call_sb, student.name))
    }

    private fun sendMessage(student: Student) {
        toast(getString(R.string.main_send_message_to, student.name))
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }

    }

}
