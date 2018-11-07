package es.iessaladillo.pedrojoya.pr007.ui.main

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr007.R
import es.iessaladillo.pedrojoya.pr007.extensions.hideSoftKeyboard
import es.iessaladillo.pedrojoya.pr007.extensions.setAfterTextChangedListener
import es.iessaladillo.pedrojoya.pr007.extensions.setOnImeActionDone
import es.iessaladillo.pedrojoya.pr007.extensions.toast
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
        txtUsername.setAfterTextChangedListener {
            lblUsername.visibility = if (txtUsername.text.isBlank()) View.INVISIBLE else View.VISIBLE
            checkIsValidForm()
        }
        txtPassword.setAfterTextChangedListener {
            lblPassword.visibility = if (txtPassword.text.isBlank()) View.INVISIBLE else View.VISIBLE
            checkIsValidForm()
        }
        txtPassword.setOnImeActionDone { logIn() }
    }

    override fun onResume() {
        super.onResume()
        currentFocus?.let { checkCurrentFocusedView(it) }
    }

    private fun checkCurrentFocusedView(view: View) {
        when (view.id) {
            R.id.txtUsername -> lblUsername.typeface = Typeface.DEFAULT_BOLD
            R.id.txtPassword -> lblPassword.typeface = Typeface.DEFAULT_BOLD
        }
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
