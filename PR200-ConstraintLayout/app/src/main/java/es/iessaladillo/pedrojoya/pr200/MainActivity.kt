package es.iessaladillo.pedrojoya.pr200

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr200.extensions.isNotBlank
import es.iessaladillo.pedrojoya.pr200.extensions.labelTextView
import es.iessaladillo.pedrojoya.pr200.extensions.onActionDone
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnAcept.setOnClickListener { login() }
        btnCancel.setOnClickListener { resetViews() }
        txtUsername.labelTextView(lblUsername, afterTextChange = { _ -> checkIsValidForm() })
        txtPassword.labelTextView(lblPassword, afterTextChange = { _ -> checkIsValidForm() })
        txtPassword.onActionDone { login() }
        // Initial state
        checkIsValidForm()
    }

    private fun login() {
        Toast.makeText(this, getString(R.string.conectando_con_el_usuario, txtUsername.text.toString()), Toast.LENGTH_SHORT).show()
    }

    private fun resetViews() {
        txtUsername.setText("")
        txtPassword.setText("")
    }

    private fun checkIsValidForm() {
        btnAcept.isEnabled = txtUsername.isNotBlank() && txtPassword.isNotBlank()
    }

}