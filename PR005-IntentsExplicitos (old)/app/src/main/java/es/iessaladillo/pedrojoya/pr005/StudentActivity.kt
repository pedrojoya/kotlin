package es.iessaladillo.pedrojoya.pr005

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr005.utils.afterTextChanged
import kotlinx.android.synthetic.main.activity_student.*

class StudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        initViews()
        getIntentData()
    }

    private fun initViews() {
        txtAge.setText(DEFAULT_AGE.toString())
        txtName.afterTextChanged { btnSend.isEnabled = isValidForm() }
        txtAge.afterTextChanged { btnSend.isEnabled = isValidForm() }
        btnSend.setOnClickListener {
            createResult()
            finish()
        }
    }

    private fun isValidForm(): Boolean =
            txtName.text.toString().isNotEmpty() &&
            txtAge.text.toString().isNotEmpty() &&
            txtAge.text.toString().toIntOrNull() != null &&
            txtAge.text.toString().toInt() <= MAX_AGE

    private fun getIntentData() {
        if (intent != null) {
            if (intent.hasExtra(EXTRA_NAME)) {
                txtName.setText(intent.getStringExtra(EXTRA_NAME))
            }
            if (intent.hasExtra(EXTRA_AGE)) {
                txtAge.setText(intent.getIntExtra(EXTRA_AGE, DEFAULT_AGE).toString())
            }
        }
    }

    private fun createResult() {
        val result = Intent()
        result.putExtra(EXTRA_NAME, txtName.text.toString())
        result.putExtra(EXTRA_AGE, txtAge.text.toString().toIntOrNull() ?: DEFAULT_AGE)
        setResult(Activity.RESULT_OK, result)
    }

    companion object {

        val EXTRA_NAME: String = "EXTRA_NAME"
        val EXTRA_AGE: String = "EXTRA_AGE"

        fun startForResult(activity: Activity, requestCode: Int, name: String, age: Int) {
            val intent = Intent(activity, StudentActivity::class.java)
            intent.putExtra(EXTRA_NAME, name)
            intent.putExtra(EXTRA_AGE, age)
            activity.startActivityForResult(intent, requestCode)
        }

    }

}
