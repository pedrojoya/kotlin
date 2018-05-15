package es.iessaladillo.pedrojoya.pr005.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.pr005.R
import es.iessaladillo.pedrojoya.pr005.extensions.afterTextChanged
import es.iessaladillo.pedrojoya.pr005.extensions.isNotBlank
import kotlinx.android.synthetic.main.activity_student.*

private const val EXTRA_NAME = "EXTRA_NAME"
private const val EXTRA_AGE = "EXTRA_AGE"

class StudentActivity : AppCompatActivity() {

    private val name by lazy {
        intent?.getStringExtra(EXTRA_NAME) ?: ""
    }

    private val age by lazy {
        intent?.getIntExtra(EXTRA_AGE, DEFAULT_AGE) ?: DEFAULT_AGE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        initViews()
    }

    private fun initViews() {
        with(txtName) {
            setText(name)
            afterTextChanged { btnSend.isEnabled = isValidForm() }
        }
        with(txtAge) {
            setText(age.toString())
            afterTextChanged { btnSend.isEnabled = isValidForm() }
        }
        btnSend.setOnClickListener { sendResult() }
    }

    private fun isValidForm() =
            txtName.isNotBlank() && txtAge.text.toString().toIntOrNull() ?: MAX_AGE + 1 <= MAX_AGE

    private fun sendResult() {
        createResult()
        finish()
    }

    private fun createResult() {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtras(bundleOf(EXTRA_NAME to txtName.text.toString(),
                    EXTRA_AGE to (txtAge.text.toString().toIntOrNull() ?: DEFAULT_AGE)))
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        // Up == Back in order not to create a new instance of MainActivity when going up.
        onBackPressed()
        return true
    }

    companion object {

        fun startForResult(activity: Activity, name: String, age: Int, requestCode: Int) {
            activity.startActivityForResult(
                    Intent(activity, StudentActivity::class.java).apply {
                        putExtras(bundleOf(EXTRA_NAME to name, EXTRA_AGE to age))
                    }, requestCode)
        }

        fun nameFromResult(intent: Intent) = intent.getStringExtra(EXTRA_NAME) ?: ""

        fun ageFromResult(intent: Intent) = intent.getIntExtra(EXTRA_AGE, DEFAULT_AGE)

    }

}

