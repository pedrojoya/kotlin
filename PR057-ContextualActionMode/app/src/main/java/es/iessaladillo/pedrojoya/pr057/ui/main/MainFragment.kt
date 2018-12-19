package es.iessaladillo.pedrojoya.pr057.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr057.R
import es.iessaladillo.pedrojoya.pr057.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr057.data.local.Database
import es.iessaladillo.pedrojoya.pr057.data.local.model.Student
import es.iessaladillo.pedrojoya.pr057.extensions.toast
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val listAdapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter().apply {
            onItemClickListener = { _, position -> showStudent(getItem(position)) }
            onShowAssignmentsListener = { position -> showAssignments(getItem(position)) }
            onShowMarksListener = { position -> showMarks(getItem(position)) }
            onShowContextualModeListener = { startContextualMode(it) }
        }
    }
    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(RepositoryImpl(Database))
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        viewModel.students.observe(viewLifecycleOwner, Observer { students ->
            listAdapter.submitList(students)
            lblEmptyView.visibility = if (students.isNotEmpty()) View.INVISIBLE else View.VISIBLE
        })
    }

    private fun setupViews() {
        setupListView()
        setupFab()
    }

    private fun setupListView() {
        lblEmptyView.setOnClickListener { viewModel.addStudent(Database.newFakeStudent()) }
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.main_lstStudents_columns))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            val itemTouchHelper = ItemTouchHelper(
                    object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

                        override fun onMove(recyclerView: RecyclerView,
                                            viewHolder: RecyclerView.ViewHolder,
                                            target: RecyclerView.ViewHolder): Boolean = false

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            viewModel.deleteStudent(listAdapter.getItem(viewHolder.adapterPosition))
                        }
                    })
            itemTouchHelper.attachToRecyclerView(this)
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

    fun call(student: Student) {
        toast(getString(R.string.main_activity_call_sb, student.name))
    }

    fun sendMessage(student: Student) {
        toast(getString(R.string.main_send_message_to, student.name))
    }

    private fun startContextualMode(position: Int) {
        (requireActivity() as AppCompatActivity).startSupportActionMode(object : ActionMode.Callback {

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                mode.title = listAdapter.getItem(position).name
                return false
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                mode.menuInflater.inflate(R.menu.fragment_main_item_contextual, menu)
                return true
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.mnuCall -> call(listAdapter.getItem(position))
                    R.id.mnuSendMessage -> sendMessage(listAdapter.getItem(position))
                }
                return true
            }

            override fun onDestroyActionMode(mode: ActionMode) { }

        })
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
