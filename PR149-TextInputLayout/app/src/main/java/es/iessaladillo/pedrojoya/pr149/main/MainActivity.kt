package es.iessaladillo.pedrojoya.pr149.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr149.R
import es.iessaladillo.pedrojoya.pr149.extensions.checkValidAfterTextChange
import es.iessaladillo.pedrojoya.pr149.extensions.isValidEmail
import es.iessaladillo.pedrojoya.pr149.extensions.isValidSpanishPhoneNumber
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        tilPhone.checkValidAfterTextChange(getString(R.string.main_activity_invalid_phone)) {
            it.isValidSpanishPhoneNumber()
        }
        tilEmail.checkValidAfterTextChange(getString(R.string.main_activity_invalid_email)) {
            it.isValidEmail()
        }
    }

}
