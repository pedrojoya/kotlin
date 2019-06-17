package es.iessaladillo.pedrojoya.pr005.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr005.R
import es.iessaladillo.pedrojoya.pr005.extensions.requireIntExtra
import es.iessaladillo.pedrojoya.pr005.ui.calendar.CalendarActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

private const val RC_CALENDAR = 1

class MainActivity : AppCompatActivity() {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private var signUpDate = LocalDate.now()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        showSignUpDate()
    }

    private fun setupViews() {
        txtSignUpDate.setOnClickListener { requestBirthDate() }
        btnShow.setOnClickListener { showData() }
    }

    private fun showData() {
        if (isValidForm()) {
            Toast.makeText(this,
                    getString(R.string.main_message, txtName.text, txtSignUpDate.text),
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidForm(): Boolean =
            txtName.text.isNotBlank() && txtSignUpDate.text.isNotBlank()

    private fun requestBirthDate() {
        CalendarActivity.startForResult(this, RC_CALENDAR, signUpDate.dayOfMonth,
                signUpDate.monthValue, signUpDate.year)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == Activity.RESULT_OK && requestCode == RC_CALENDAR && intent != null) {
            getReturnData(intent)
        }
    }

    private fun getReturnData(intent: Intent) {
        val day = intent.requireIntExtra(CalendarActivity.EXTRA_DAY)
        val month = intent.requireIntExtra(CalendarActivity.EXTRA_MONTH)
        val year = intent.requireIntExtra(CalendarActivity.EXTRA_YEAR)
        signUpDate = LocalDate.of(year, month, day)
        showSignUpDate()
    }

    private fun showSignUpDate() {
        txtSignUpDate.setText(dateTimeFormatter.format(signUpDate))
    }

}
