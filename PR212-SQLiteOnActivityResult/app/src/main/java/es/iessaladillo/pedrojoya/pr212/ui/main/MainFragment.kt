package es.iessaladillo.pedrojoya.pr212.ui.main

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr212.R
import es.iessaladillo.pedrojoya.pr212.base.setOnItemClickListener
import es.iessaladillo.pedrojoya.pr212.data.Repository
import es.iessaladillo.pedrojoya.pr212.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr212.data.local.DbHelper
import es.iessaladillo.pedrojoya.pr212.data.local.StudentDao
import es.iessaladillo.pedrojoya.pr212.data.local.model.Student
import es.iessaladillo.pedrojoya.pr212.extensions.setOnSwipeRightListener
import es.iessaladillo.pedrojoya.pr212.extensions.toast
import es.iessaladillo.pedrojoya.pr212.ui.student.StudentActivity
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.ref.WeakReference

private const val RC_ADD_STUDENT = 1
private const val RC_EDIT_STUDENT = 2

class MainFragment : Fragment() {

    private val fab: FloatingActionButton by lazy {
        ActivityCompat.requireViewById<FloatingActionButton>(requireActivity(), R.id.fab)
    }
    private val listAdapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter().apply {
            setOnItemClickListener { _, position -> editStudent(getItem(position)) }
            emptyView = lblEmptyView
        }
    }
    private val repository: Repository by lazy {
        RepositoryImpl(StudentDao(requireContext(), DbHelper.getInstance(requireContext())))
    }
    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        if (savedInstanceState != null) {
            listAdapter.submitList(viewModel.getStudents(false))
        } else {
            loadStudents()
        }
    }

    private fun initViews() {
        setupFab()
        setupRecyclerView()
    }

    private fun setupFab() {
        fab.setOnClickListener { addStudent() }
    }

    private fun setupRecyclerView() {
        lstStudents.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,
                    false)
            addItemDecoration(DividerItemDecoration(requireContext(),
                    LinearLayoutManager.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
            setOnSwipeRightListener { viewHolder ->  deleteStudent(viewHolder.adapterPosition) }
        }
    }

    private fun addStudent() {
        StudentActivity.startForResult(this, RC_ADD_STUDENT)
    }

    private fun editStudent(student: Student) {
        StudentActivity.startForResult(this, student.id, RC_EDIT_STUDENT)
    }

    private fun deleteStudent(position: Int) {
        DeleteStudentTask(this, repository).execute(listAdapter.getItem(position))
    }

    private fun loadStudents() {
        LoadStudentsTask(this, viewModel).execute()
    }

    private fun showSuccessDeletingStudent() {
        toast(R.string.main_fragment_student_deleted)
    }

    private fun showErrorDeletingStudent() {
        toast(R.string.main_fragment_error_deleting_student)
    }

    override fun onDestroy() {
        repository.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if ((requestCode == RC_ADD_STUDENT || requestCode == RC_EDIT_STUDENT) &&
                resultCode == Activity.RESULT_OK) {
            loadStudents()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private class LoadStudentsTask(mainFragment: MainFragment, private val viewModel: MainFragmentViewModel) : AsyncTask<Void, Void, List<Student>>() {

        private val mainFragmentWeakReference: WeakReference<MainFragment> = WeakReference(mainFragment)

        override fun doInBackground(vararg args: Void): List<Student> {
            return viewModel.getStudents(true)
        }

        override fun onPostExecute(students: List<Student>) {
            mainFragmentWeakReference.get()?.listAdapter?.submitList(students)
        }

    }

    private class DeleteStudentTask(mainFragment: MainFragment, private val repository: Repository) :
            AsyncTask<Student, Void, Boolean>() {

        private val mainFragmentWeakReference = WeakReference(mainFragment)

        override fun doInBackground(vararg students: Student): Boolean {
            return repository.deleteStudent(students[0].id)
        }

        override fun onPostExecute(success: Boolean) {
            val mainFragment = mainFragmentWeakReference.get()
            if (success) {
                mainFragment?.showSuccessDeletingStudent()
                mainFragment?.loadStudents()
            } else {
                mainFragment?.showErrorDeletingStudent()
            }
        }

    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
