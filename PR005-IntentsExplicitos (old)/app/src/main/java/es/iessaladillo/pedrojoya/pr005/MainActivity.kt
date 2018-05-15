package es.iessaladillo.pedrojoya.pr005

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val RC_STUDENT = 1

    var name: String = ""
    var age: Int = DEFAULT_AGE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnRequest.setOnClickListener { requestData() }
    }

    private fun requestData() {
        StudentActivity.startForResult(this, RC_STUDENT, name, age)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == RC_STUDENT) {
            getReturnData(data)
        }

    }

    private fun getReturnData(intent: Intent?) {
        if (intent != null) {
            if (intent.hasExtra(StudentActivity.EXTRA_NAME)) {
                name = intent.getStringExtra(StudentActivity.EXTRA_NAME)
            }
            if (intent.hasExtra(StudentActivity.EXTRA_AGE)) {
                age = intent.getIntExtra(StudentActivity.EXTRA_AGE, DEFAULT_AGE)
            }
        }
        lblData.text = getString(R.string.main_activity_student_data, name, age)
    }

}

