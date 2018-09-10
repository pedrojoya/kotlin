package es.iessaladillo.pedrojoya.pr156.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr156.DEFAULT_AGE
import es.iessaladillo.pedrojoya.pr156.R
import es.iessaladillo.pedrojoya.pr156.data.models.Student
import es.iessaladillo.pedrojoya.pr156.ui.student.StudentActivity
import kotlinx.android.synthetic.main.activity_main.*

private const val RC_STUDENT = 1

class MainActivity : AppCompatActivity() {

    // This should be preserved during configuration change (not explained yet)
    private var student: Student = Student("", DEFAULT_AGE)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnRequest.setOnClickListener { requestData() }
    }

    private fun requestData() {
        StudentActivity.startForResult(this, student, RC_STUDENT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == RC_STUDENT && data != null) {
            obtainResultData(data)
        }
    }

    private fun obtainResultData(intent: Intent) {
        student = StudentActivity.studentFromResult(intent)
        showStudent()
    }

    private fun showStudent() {
        lblData.text = getString(R.string.main_activity_student_data, student.name, student.age)
    }

}
