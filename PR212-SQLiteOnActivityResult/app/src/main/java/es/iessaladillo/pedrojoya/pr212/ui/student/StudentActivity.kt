package es.iessaladillo.pedrojoya.pr212.ui.student

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr212.R
import es.iessaladillo.pedrojoya.pr212.extensions.extraLong
import kotlinx.android.synthetic.main.activity_student.*

const val EXTRA_STUDENT_ID = "EXTRA_STUDENT_ID"
private const val TAG_STUDENT_FRAGMENT = "TAG_STUDENT_FRAGMENT"

class StudentActivity : AppCompatActivity() {

    private val studentId: Long by extraLong(EXTRA_STUDENT_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        initViews()
        loadFragment()
    }

    private fun initViews() {
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadFragment() {
        if (supportFragmentManager.findFragmentByTag(TAG_STUDENT_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flContent, if (studentId > 0)
                    StudentFragment.newInstance(studentId) else StudentFragment.newInstance(),
                        TAG_STUDENT_FRAGMENT)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun startForResult(fragment: Fragment, requestCode: Int) {
            fragment.startActivityForResult(Intent(fragment.activity, StudentActivity::class.java),
                    requestCode)
        }

        fun startForResult(fragment: Fragment, studentId: Long, requestCode: Int) {
            fragment.startActivityForResult(Intent(fragment.activity, StudentActivity::class
                    .java).apply { putExtra(EXTRA_STUDENT_ID, studentId) }, requestCode)
        }

    }

}
