package es.iessaladillo.pedrojoya.pr200.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr200.R
import es.iessaladillo.pedrojoya.pr200.extensions.isNotBlank
import es.iessaladillo.pedrojoya.pr200.extensions.labelTextView
import es.iessaladillo.pedrojoya.pr200.extensions.onActionDone
import es.iessaladillo.pedrojoya.pr200.extensions.toast
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
        toast(getString(R.string.conectando_con_el_usuario, txtUsername.text.toString()))
    }

    private fun resetViews() {
        txtUsername.setText("")
        txtPassword.setText("")
    }

    private fun checkIsValidForm() {
        btnAcept.isEnabled = txtUsername.isNotBlank() && txtPassword.isNotBlank()
    }

}