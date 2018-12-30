package es.iessaladillo.pedrojoya.pr086.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.base.EventObserver
import es.iessaladillo.pedrojoya.pr086.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr086.data.local.Database
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import es.iessaladillo.pedrojoya.pr086.extensions.toast
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(RepositoryImpl(Database))
    }
    private val listAdapter: MainFragmentAdapter by lazy { MainFragmentAdapter(viewModel) }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        setupListView()
        setupFab()
    }

    private fun setupListView() {
        lblEmptyView.setOnClickListener { viewModel.addStudent(Database.newFakeStudent()) }
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

    private fun observeViewModel() {
        observeStudents()
        observeNavigation()
    }

    private fun observeStudents() {
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            listAdapter.submitList(students)
            lblEmptyView.visibility = if (students.isEmpty()) View.VISIBLE else View.INVISIBLE
        })
    }

    private fun observeNavigation() {
        viewModel.navigateToStudentDetail.observe(viewLifecycleOwner,
                EventObserver { showStudent(it) })
        viewModel.navigateToStudentAssignments.observe(viewLifecycleOwner,
                EventObserver { showAssignments(it) })
        viewModel.navigateToStudentMarks.observe(viewLifecycleOwner,
                EventObserver { showMarks(it) })
        viewModel.navigateToCallStudent.observe(viewLifecycleOwner,
                EventObserver { call(it) })
        viewModel.navigateToSendMessageToStudent.observe(viewLifecycleOwner,
                EventObserver { sendMessage(it) })
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

        fun newInstance(): MainFragment = MainFragment()

    }

}
