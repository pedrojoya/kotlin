package es.iessaladillo.pedrojoya.pr211.ui.student

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr211.App
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.data.Repository
import es.iessaladillo.pedrojoya.pr211.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr211.data.model.Student
import es.iessaladillo.pedrojoya.pr211.extensions.checkValid
import es.iessaladillo.pedrojoya.pr211.extensions.extraLong
import es.iessaladillo.pedrojoya.pr211.extensions.onActionDone
import es.iessaladillo.pedrojoya.pr211.extensions.viewModelProvider
import kotlinx.android.synthetic.main.fragment_student.*
import java.lang.ref.WeakReference

class StudentFragment : Fragment() {

    private val studentId: Long by extraLong(EXTRA_STUDENT_ID)
    private val repository: Repository by lazy { RepositoryImpl(App.database.studentDao()) }
    private val viewModel: StudentFragmentViewModel by viewModelProvider {
        StudentFragmentViewModel(repository)
    }

    private fun isValidForm(): Boolean =
                    tilName.checkValid(getString(R.string.student_fragment_required_field)) &&
                    tilGrade.checkValid(getString(R.string.student_fragment_required_field)) &&
                    tilPhone.checkValid(getString(R.string.student_fragment_required_field))


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View?) {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { _ -> saveStudent() }
        txtAddress.onActionDone { saveStudent() }
//        loadGrades()
        updateTitle()
        if (studentId != 0L) {
            loadStudent(studentId)
        }
    }

/*
    private fun loadGrades() {
        txtGrade.run {
            setAdapter(ArrayAdapter.createFromResource(requireActivity(),
                    R.array.grades, android.R.layout.simple_list_item_1))
            setOnItemSelectedListener { item, _ -> txtGrade.setText(item.toString()) }
        }
    }
*/

    private fun updateTitle() {
        requireActivity().setTitle(if (studentId != 0L) R.string.student_fragment_edit_student else R.string
                .student_fragment_add_student)
    }

    private fun loadStudent(studentId: Long) {
        viewModel.getStudent(studentId).observe(viewLifecycleOwner, Observer<Student> { student ->
            if (student != null) showStudent(student)
            else showErrorLoadingStudentAndFinish()
        })
    }

    private fun showErrorLoadingStudentAndFinish() {
        Toast.makeText(requireActivity(), R.string.student_fragment_error_loading_student, Toast.LENGTH_LONG).show()
        requireActivity().finish()
    }

    private fun saveStudent() {
        if (isValidForm()) {
            val student = createStudent()
            if (studentId != 0L) {
                student.id = studentId
                updateStudent(student)
            } else {
                addStudent(student)
            }
        }
    }

    private fun addStudent(student: Student) {
        AddStudentTask(this, repository).execute(student)
    }

    private fun showSuccessAddingStudent() {
        Toast.makeText(requireActivity(), getString(R.string.student_fragment_student_added),
                Toast.LENGTH_SHORT).show()
    }

    private fun showErrorAddingStudent() {
        Toast.makeText(requireActivity(), getString(R.string.student_fragment_error_adding_student),
                Toast.LENGTH_SHORT).show()
    }

    private fun updateStudent(student: Student) {
        EditStudentTask(this, repository).execute(student)
    }

    private fun showSucessUpdatingStudent() {
        Toast.makeText(requireActivity(), getString(R.string.student_fragment_student_updated),
                Toast.LENGTH_SHORT).show()

    }

    private fun showErrorUpdatingStudent() {
        Toast.makeText(requireActivity(), getString(R.string.student_fragment_error_updating_student),
                Toast.LENGTH_SHORT).show()
    }

    private fun showStudent(student: Student) {
        student.run {
            txtName.setText(name)
            txtGrade.setText(grade)
            txtPhone.setText(phone)
            txtAddress.setText(address)
        }
    }

    private fun createStudent(): Student =
            Student(0L, txtName.text.toString(),
                    txtPhone.text.toString(),
                    txtGrade.text.toString(),
                    txtAddress.text.toString())

    private class AddStudentTask(studentFragment: StudentFragment, private val repository: Repository)
        : AsyncTask<Student, Void, Long>() {

        private val studentFragmentWeakReference: WeakReference<StudentFragment> = WeakReference(studentFragment)

        override fun doInBackground(vararg students: Student): Long {
            return repository.insertStudent(students[0])
        }

        override fun onPostExecute(studentId: Long) {
            studentFragmentWeakReference.get()?.run {
                if (studentId >= 0) {
                    showSuccessAddingStudent()
                } else {
                    showErrorAddingStudent()
                }
                requireActivity().finish()
            }
        }

    }

    private class EditStudentTask(studentFragment: StudentFragment, private val repository:
    Repository) : AsyncTask<Student, Void, Int>() {

        private val studentFragmentWeakReference: WeakReference<StudentFragment> = WeakReference(studentFragment)


        override fun doInBackground(vararg students: Student): Int? {
            return repository.updateStudent(students[0])
        }

        override fun onPostExecute(updates: Int) {
            studentFragmentWeakReference.get()?.run {
                if (updates == 1) {
                    showSucessUpdatingStudent()
                } else {
                    showErrorUpdatingStudent()
                }
                requireActivity().finish()
            }
        }

    }

    companion object {

        @Suppress("unused")
        fun newInstance(): StudentFragment {
            return StudentFragment()
        }

        fun newInstance(studentId: Long): StudentFragment =
                StudentFragment().apply {
                    arguments = Bundle().apply {
                        putLong(EXTRA_STUDENT_ID, studentId)
                    }
                }

    }

}
