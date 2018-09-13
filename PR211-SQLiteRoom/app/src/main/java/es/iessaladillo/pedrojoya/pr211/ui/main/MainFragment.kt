package es.iessaladillo.pedrojoya.pr211.ui.main

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr211.App
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr211.data.Repository
import es.iessaladillo.pedrojoya.pr211.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr211.data.model.Student
import es.iessaladillo.pedrojoya.pr211.extensions.setOnSwipeRightListener
import es.iessaladillo.pedrojoya.pr211.extensions.viewModelProvider
import es.iessaladillo.pedrojoya.pr211.ui.student.StudentActivity
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.ref.WeakReference

class MainFragment : Fragment() {

    private val listAdapter: MainFragmentAdapter by lazy { MainFragmentAdapter() }
    private val repository: Repository by lazy { RepositoryImpl(App.database.studentDao()) }
    private val viewModel: MainFragmentViewModel by viewModelProvider { MainFragmentViewModel(repository) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setupFab()
        setupRecyclerView()
        viewModel.getStudents().observe(viewLifecycleOwner, Observer<List<Student>> { students ->
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
                setOnItemClickListener { _, position -> editStudent(getItem(position)) }
                emptyView = lblEmptyView
            }
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            setOnSwipeRightListener { viewHolder -> deleteStudent(viewHolder.adapterPosition) }
        }
    }

    private fun addStudent() {
        StudentActivity.start(requireActivity())
    }

    private fun editStudent(student: Student) {
        StudentActivity.start(requireActivity(), student.id)
    }

    private fun deleteStudent(position: Int) {
        val student = listAdapter.getItem(position)
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
