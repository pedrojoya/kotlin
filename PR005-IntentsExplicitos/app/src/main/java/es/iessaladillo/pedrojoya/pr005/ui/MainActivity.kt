package es.iessaladillo.pedrojoya.pr005.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr005.R
import kotlinx.android.synthetic.main.activity_main.*

const val DEFAULT_AGE = 18
const val MAX_AGE = 150
const val RC_STUDENT = 1

class MainActivity : AppCompatActivity() {

    // NOTE: These two fiels should be saved on configuration change (not explained yet)
    private var name = ""
    private var age = DEFAULT_AGE

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnRequest.setOnClickListener {
            StudentActivity.startForResult(this@MainActivity, name, age, RC_STUDENT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK && requestCode == RC_STUDENT) {
            getResultData(data)
        }
    }

    private fun getResultData(intent: Intent) {
        name = StudentActivity.nameFromResult(intent)
        age = StudentActivity.ageFromResult(intent)
        lblData.text = getString(R.string.main_activity_student_data, name, age)
    }

}