package es.iessaladillo.pedrojoya.pr008

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr008.extensions.isNotBlank
import es.iessaladillo.pedrojoya.pr008.extensions.labelTextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val isValid: Boolean
        get() = txtUsername.isNotBlank() && txtPassword.isNotBlank()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnLogin.setOnClickListener { signIn() }
        btnCancel.setOnClickListener { resetViews() }
        txtUsername.labelTextView(lblUsername, afterTextChange = { checkIsValidForm() })
        txtPassword.labelTextView(lblPassword, afterTextChange = { checkIsValidForm() })
    }

    private fun signIn() {
        Toast.makeText(this, getString(R.string.main_activity_connected,
                txtUsername.text.toString()), Toast.LENGTH_SHORT).show()
    }

    private fun resetViews() {
        txtUsername.setText("")
        txtPassword.setText("")
    }

    private fun checkIsValidForm() {
        btnLogin.isEnabled = isValid
    }

}
