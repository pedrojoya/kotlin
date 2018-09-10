package es.iessaladillo.pedrojoya.pr211.ui.student

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.extensions.extraLong
import kotlinx.android.synthetic.main.activity_student.*

private const val TAG_STUDENT_FRAGMENT = "TAG_STUDENT_FRAGMENT"
const val EXTRA_STUDENT_ID = "EXTRA_STUDENT_ID"


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
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun loadFragment() {
        if (supportFragmentManager.findFragmentByTag(TAG_STUDENT_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flContent, StudentFragment.newInstance(studentId), TAG_STUDENT_FRAGMENT)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, StudentActivity::class.java))
        }

        fun start(context: Context, studentId: Long) {
            context.startActivity(Intent(context, StudentActivity::class.java).apply {
                putExtra(EXTRA_STUDENT_ID, studentId)
            })
        }

    }

}
