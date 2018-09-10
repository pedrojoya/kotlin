package es.iessaladillo.pedrojoya.pr212.ui.student

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr212.R
import es.iessaladillo.pedrojoya.pr212.data.Repository
import es.iessaladillo.pedrojoya.pr212.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr212.data.local.DbHelper
import es.iessaladillo.pedrojoya.pr212.data.local.StudentDao
import es.iessaladillo.pedrojoya.pr212.data.model.Student
import es.iessaladillo.pedrojoya.pr212.extensions.checkValid
import es.iessaladillo.pedrojoya.pr212.extensions.extraLong
import es.iessaladillo.pedrojoya.pr212.extensions.onAction
import es.iessaladillo.pedrojoya.pr212.extensions.toast
import kotlinx.android.synthetic.main.fragment_student.*
import java.lang.ref.WeakReference

class StudentFragment : Fragment() {

    private val fab: FloatingActionButton by lazy {
        ActivityCompat.requireViewById<FloatingActionButton>(requireActivity(), R.id.fab)
    }
    private val studentId: Long by extraLong(EXTRA_STUDENT_ID)
    private val repository: Repository by lazy {
        RepositoryImpl(StudentDao(requireContext(), DbHelper.getInstance(requireContext().applicationContext)))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_student, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener { saveStudent() }
        txtAddress.onAction {
            saveStudent()
        }
        //loadGrades()
        updateTitle()
        if (studentId > 0) {
            loadStudent(studentId)
        }
    }

    private fun updateTitle() {
        requireActivity().setTitle(
                if (studentId > 0) R.string.student_fragment_edit_student
                else R.string.student_fragment_add_student)
    }

    private fun loadStudent(studentId: Long) {
        LoadStudentTask(this, repository).execute(studentId)
    }

    private fun showErrorLoadingStudentAndFinish() {
        toast(R.string.student_fragment_error_loading_student, Toast.LENGTH_LONG)
        requireActivity().finish()
    }

    private fun saveStudent() {
        if (isValidForm()) {
            val student = createStudent()
            if (studentId > 0) {
                student.id = studentId
                updateStudent(student)
            } else {
                addStudent(student)
            }
        }
    }

    private fun isValidForm(): Boolean {
        // Can't be done with && because of short-circuit evaluation.
        var valid = true
        if (!tilName.checkValid(getString(R.string.student_fragment_required_field)))
            valid = false
        if (!tilPhone.checkValid(getString(R.string.student_fragment_required_field)))
            valid = false
        if (!tilGrade.checkValid(getString(R.string.student_fragment_required_field)))
            valid = false
        return valid
    }

    private fun addStudent(student: Student) {
        AddStudentTask(this, repository).execute(student)
    }

    private fun showSuccessAddingStudent() {
        toast(R.string.student_fragment_student_added)
    }

    private fun showErrorAddingStudent() {
        toast(R.string.student_fragment_error_adding_student)
    }

    private fun updateStudent(student: Student) {
        EditStudentTask(this, repository).execute(student)
    }

    private fun showSucessUpdatingStudent() {
        toast(R.string.student_fragment_student_updated)
    }

    private fun showErrorUpdatingStudent() {
        toast(R.string.student_fragment_error_updating_student)
    }

    private fun showStudent(student: Student) {
        with(student) {
            txtName.setText(name)
            txtGrade.setText(grade)
            txtPhone.setText(phone)
            txtAddress.setText(address)
        }
    }

    private fun createStudent(): Student =
            Student(0, txtName.text.toString(), txtPhone.text.toString(),
                    txtGrade.text.toString(), txtAddress.text.toString())

    private fun sendResultOkAndFinish() {
        requireActivity().setResult(Activity.RESULT_OK, Intent())
        requireActivity().finish()
    }

    private class LoadStudentTask(studentFragment: StudentFragment,
                                  private val repository: Repository) :
            AsyncTask<Long, Void, Student?>() {

        private val studentFragmentWeakReference = WeakReference(studentFragment)

        override fun doInBackground(vararg studentIds: Long?): Student? =
                repository.getStudent(studentIds[0] as Long)

        override fun onPostExecute(student: Student?) {
            if (student != null) {
                studentFragmentWeakReference.get()?.showStudent(student)
            } else {
                studentFragmentWeakReference.get()?.showErrorLoadingStudentAndFinish()
            }
        }

    }

    private class AddStudentTask(studentFragment: StudentFragment,
                                 private val repository: Repository) :
            AsyncTask<Student, Void, Long>() {

        private val studentFragmentWeakReference =
                WeakReference(studentFragment)

        override fun doInBackground(vararg students: Student): Long {
            return repository.addStudent(students[0])
        }

        override fun onPostExecute(studentId: Long) {
            val studentFragment = studentFragmentWeakReference.get()
            if (studentId >= 0) {
                studentFragment?.showSuccessAddingStudent()
            } else {
                studentFragment?.showErrorAddingStudent()
            }
            studentFragment?.sendResultOkAndFinish()
        }

    }

    private class EditStudentTask(studentFragment: StudentFragment,
                                  private val repository: Repository) :
            AsyncTask<Student, Void, Boolean>() {

        private val studentFragmentWeakReference =
                WeakReference(studentFragment)

        override fun doInBackground(vararg students: Student): Boolean {
            return repository.updateStudent(students[0])
        }

        override fun onPostExecute(success: Boolean) {
            val studentFragment = studentFragmentWeakReference.get()
            if (success) {
                studentFragment?.showSucessUpdatingStudent()
            } else {
                studentFragment?.showErrorUpdatingStudent()
            }
            studentFragment?.sendResultOkAndFinish()
        }

    }

    companion object {

        fun newInstance(): StudentFragment = StudentFragment()

        fun newInstance(studentId: Long): StudentFragment = StudentFragment().apply {
            arguments = bundleOf(EXTRA_STUDENT_ID to studentId)
        }

    }

}
