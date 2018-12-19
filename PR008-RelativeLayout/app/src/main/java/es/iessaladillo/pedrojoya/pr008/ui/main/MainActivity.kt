package es.iessaladillo.pedrojoya.pr008.ui.main

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import es.iessaladillo.pedrojoya.pr008.R
import es.iessaladillo.pedrojoya.pr008.extensions.doOnImeAction
import es.iessaladillo.pedrojoya.pr008.extensions.hideSoftKeyboard
import es.iessaladillo.pedrojoya.pr008.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        checkInitialState()
    }

    private fun setupViews() {
        btnLogin.setOnClickListener { logIn() }
        btnCancel.setOnClickListener { resetViews() }
        txtUsername.setOnFocusChangeListener { _, hasFocus ->
            lblUsername.typeface = if (hasFocus) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        }
        txtPassword.setOnFocusChangeListener { _, hasFocus ->
            lblPassword.typeface = if (hasFocus) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        }
        txtUsername.doAfterTextChanged {
            lblUsername.visibility = if (txtUsername.text.isBlank()) View.INVISIBLE else View.VISIBLE
            checkIsValidForm()
        }
        txtPassword.doAfterTextChanged {
            lblPassword.visibility = if (txtPassword.text.isBlank()) View.INVISIBLE else View.VISIBLE
            checkIsValidForm()
        }
        txtPassword.doOnImeAction { logIn() }
    }

    private fun checkInitialState() {
        checkIsValidForm()
        lblUsername.visibility = if (TextUtils.isEmpty(txtUsername.text)) View.INVISIBLE else View.VISIBLE
        lblPassword.visibility = if (TextUtils.isEmpty(txtPassword.text)) View.INVISIBLE else View.VISIBLE
    }

    private fun isValidForm(): Boolean = txtUsername.text.isNotBlank() && txtPassword.text.isNotBlank()

    private fun logIn() {
        if (isValidForm()) {
            hideSoftKeyboard()
            toast(getString(R.string.main_login, txtUsername.text))
        }
    }

    private fun resetViews() {
        txtUsername.setText("")
        txtPassword.setText("")
    }

    private fun checkIsValidForm() {
        btnLogin.isEnabled = isValidForm()
    }

}
