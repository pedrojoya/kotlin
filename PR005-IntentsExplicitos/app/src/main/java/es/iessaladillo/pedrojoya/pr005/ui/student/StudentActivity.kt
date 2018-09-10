package es.iessaladillo.pedrojoya.pr005.ui.student

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.pr005.DEFAULT_AGE
import es.iessaladillo.pedrojoya.pr005.MAX_AGE
import es.iessaladillo.pedrojoya.pr005.R
import es.iessaladillo.pedrojoya.pr005.extensions.*
import kotlinx.android.synthetic.main.activity_student.*

private const val EXTRA_NAME = "EXTRA_NAME"
private const val EXTRA_AGE = "EXTRA_AGE"

class StudentActivity : AppCompatActivity() {

    private val name by extraString(EXTRA_NAME, "")
    private val age by extraInt(EXTRA_AGE, DEFAULT_AGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        initViews()
    }

    private fun initViews() {
        txtName.run {
            setText(name)
            // Opción más completa porque permite especifcar más listeners del TextWatcher
            // además de afterTextChanged.
            addTextChangeListener {
                afterTextChanged {
                    btnSend.isEnabled = isValidForm()
                }
            }
        }
        txtAge.run {
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

