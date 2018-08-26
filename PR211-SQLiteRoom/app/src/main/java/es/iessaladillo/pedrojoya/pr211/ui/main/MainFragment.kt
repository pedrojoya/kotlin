package es.iessaladillo.pedrojoya.pr211.ui.main

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr211.App
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.data.Repository
import es.iessaladillo.pedrojoya.pr211.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr211.data.model.Student
import es.iessaladillo.pedrojoya.pr211.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr211.ui.student.StudentActivity
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.ref.WeakReference

class MainFragment : Fragment() {

    private val listAdapter: MainFragmentListAdapter by lazy { MainFragmentListAdapter() }
    private lateinit var repository: Repository
    private val viewModel: MainActivityViewModel by lazy {
        requireActivity().getViewModel {
            MainActivityViewModel(repository)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repository = RepositoryImpl(App.database.studentDao())
        initViews()
    }

    private fun initViews() {
        setupFab()
        setupRecyclerView()
        viewModel.getStudents().observe(requireActivity(), Observer<List<Student>> { students ->
            if (students != null) {
                // New data list for listAdapter (with automatic diffcallback calculations).
                listAdapter.submitList(students)
            }
        })
    }

    private fun setupFab() {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { _ -> addStudent() }
    }

    private fun setupRecyclerView() {
        lstStudents.apply {
            setHasFixedSize(true)
            adapter = listAdapter.apply {
                setOnItemClickListener { _, student, _ -> editStudent(student) }
                setEmptyView(lblEmptyView)
            }
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            ItemTouchHelper(
                    object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                            ItemTouchHelper.RIGHT) {
                        override fun onMove(recyclerView: RecyclerView,
                                            viewHolder: RecyclerView.ViewHolder, target:
                                            RecyclerView.ViewHolder): Boolean = false

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            deleteStudent(viewHolder.adapterPosition)
                        }
                    }).attachToRecyclerView(this)
        }
    }

    private fun addStudent() {
        StudentActivity.start(requireActivity())
    }

    private fun editStudent(student: Student) {
        StudentActivity.start(requireActivity(), student.id)
    }

    private fun deleteStudent(position: Int) {
        val student = listAdapter.getItemAtPosition(position)
        DeleteStudentTask(this, repository).execute(student)
    }

    private fun showSuccessDeletingStudent() {
        Toast.makeText(requireActivity(), R.string.main_fragment_student_deleted, Toast.LENGTH_SHORT)
                .show()
    }

    private fun showErrorDeletingStudent() {
        Toast.makeText(requireActivity(), R.string.main_fragment_error_deleting_student,
                Toast.LENGTH_SHORT).show()
    }

    private class DeleteStudentTask(
            mainFragment: MainFragment,
            private val repository: Repository) : AsyncTask<Student, Void, Int>() {

        private val mainFragmentWeakReference: WeakReference<MainFragment> = WeakReference(mainFragment)

        override fun doInBackground(vararg students: Student): Int? {
            return repository.deleteStudent(students[0])
        }

        override fun onPostExecute(deletions: Int?) {
            mainFragmentWeakReference.get()?.apply {
                if (deletions == 1) {
                    showSuccessDeletingStudent()
                } else {
                    showErrorDeletingStudent()
                }
            }
        }

    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
