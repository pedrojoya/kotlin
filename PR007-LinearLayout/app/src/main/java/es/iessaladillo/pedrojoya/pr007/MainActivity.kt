package es.iessaladillo.pedrojoya.pr007

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr007.extensions.isNotBlank
import es.iessaladillo.pedrojoya.pr007.extensions.labelTextView
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
        btnAccept.setOnClickListener { signIn() }
        btnCancel.setOnClickListener { resetViews() }
        txtUsername.labelTextView(lblUsername, { checkIsValidForm() })
        txtPassword.labelTextView(lblPassword, { checkIsValidForm() })
    }

    private fun signIn() {
        toast(getString(R.string.main_activity_signing_in, txtUsername.text.toString()))
    }

    private fun resetViews() {
        txtUsername.setText("")
        txtPassword.setText("")
    }

    private fun checkIsValidForm() {
        btnAccept.isEnabled = isValid
    }

}
