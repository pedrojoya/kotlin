package es.iessaladillo.pedrojoya.pr156.ui.student

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.pr156.R
import es.iessaladillo.pedrojoya.pr156.data.DEFAULT_AGE
import es.iessaladillo.pedrojoya.pr156.data.Student
import kotlinx.android.synthetic.main.activity_student.*

private const val EXTRA_STUDENT = "EXTRA_STUDENT"

class StudentActivity : AppCompatActivity() {

    private val student by lazy {
        intent?.getParcelableExtra(EXTRA_STUDENT) ?: Student("", DEFAULT_AGE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_student)
        initViews()
    }

    private fun initViews() {
        btnSend.setOnClickListener { finish() }
        txtName.setText(student.name)
        txtAge.setText(student.age.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun finish() {
        buildResult()
        super.finish()
    }

    private fun buildResult() {
        viewsToStudent()
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtras(bundleOf(EXTRA_STUDENT to student))
        })
    }

    private fun viewsToStudent() {
        student.name = txtName.text.toString()
        student.age = txtAge.text.toString().toIntOrNull() ?: DEFAULT_AGE
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun startForResult(activity: Activity, student: Student, requestCode: Int) {
            activity.startActivityForResult(Intent(activity, StudentActivity::class.java).apply {
                putExtras(bundleOf(EXTRA_STUDENT to student))
            }, requestCode)
        }

        fun studentFromResult(intent: Intent): Student = intent.getParcelableExtra(EXTRA_STUDENT)

    }

}
