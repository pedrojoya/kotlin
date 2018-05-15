package es.iessaladillo.pedrojoya.pr200

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.core.widget.toast
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
        txtUsername.labelTextView(lblUsername, { _ -> checkIsValidForm() })
        txtPassword.labelTextView(lblPassword, { _ -> checkIsValidForm() })
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