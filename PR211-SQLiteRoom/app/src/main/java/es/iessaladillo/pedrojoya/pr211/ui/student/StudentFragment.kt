package es.iessaladillo.pedrojoya.pr211.ui.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr211.data.local.AppDatabase
import es.iessaladillo.pedrojoya.pr211.data.local.model.Student
import es.iessaladillo.pedrojoya.pr211.extensions.checkValid
import es.iessaladillo.pedrojoya.pr211.extensions.doOnImeAction
import es.iessaladillo.pedrojoya.pr211.extensions.extraLong
import kotlinx.android.synthetic.main.fragment_student.*

private const val EXTRA_STUDENT_ID: String = "EXTRA_STUDENT_ID"

class StudentFragment : Fragment() {

    private val studentId: Long by extraLong(EXTRA_STUDENT_ID)
    private val viewModel: StudentFragmentViewModel by viewModels {
        StudentFragmentViewModelFactory(RepositoryImpl(AppDatabase.getInstance(requireContext()).studentDao()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_student, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupFab()
        txtAddress.doOnImeAction { saveStudent() }
        if (studentId != 0L) {
            observeStudent()
        }
    }

    private fun setupFab() {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.run {
            setImageResource(R.drawable.ic_save_white_24dp)
            setOnClickListener { saveStudent() }
        }
    }

    private fun observeStudent() {
        viewModel.getStudent(studentId).observe(viewLifecycleOwner, Observer { showStudent(it) })
    }

    private fun setupToolbar() {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.run {
            title = if (studentId != 0L)
                getString(R.string.student_edit_student)
            else
                getString(R.string.student_add_student)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun saveStudent() {
        if (isValidForm()) {
            val student = createStudent()
            if (studentId != 0L) {
                student.id = studentId
                viewModel.updateStudent(student)
            } else {
                viewModel.insertStudent(student)
            }
        }
    }

    private fun isValidForm(): Boolean =
            tilName.checkValid(getString(R.string.student_required_field)) &&
                    tilGrade.checkValid(getString(R.string.student_required_field)) &&
                    tilPhone.checkValid(getString(R.string.student_required_field))

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

    companion object {

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
