package es.iessaladillo.pedrojoya.pr012.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr012.R
import es.iessaladillo.pedrojoya.pr012.data.local.Database
import es.iessaladillo.pedrojoya.pr012.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr012.data.local.model.Student
import es.iessaladillo.pedrojoya.pr012.extensions.toast
import es.iessaladillo.pedrojoya.pr012.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(RepositoryImpl(Database))
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        lstStudents.run {
            emptyView = lblEmpty
            adapter = MainActivityAdapter(viewModel.data).apply {
                setOnCallListener { _, student, _ -> callStudent(student) }
                setOnMarksListener { _, student, _ -> showStudentMarks(student) }
            }
        }
    }

    private fun showStudentMarks(student: Student) {
        toast(getString(R.string.main_activity_show_sb_marks, student.name))
    }

    private fun callStudent(student: Student) {
        toast(getString(R.string.main_activity_call_sb, student.name))
    }

}

