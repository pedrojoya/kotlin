package es.iessaladillo.pedrojoya.pr200.ui.main

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr200.R
import es.iessaladillo.pedrojoya.pr200.extensions.labelTextView
import es.iessaladillo.pedrojoya.pr200.extensions.onActionDone
import es.iessaladillo.pedrojoya.pr200.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        btnAccept.setOnClickListener { login() }
        btnCancel.setOnClickListener { resetViews() }
        txtUsername.labelTextView(lblUsername, afterTextChange = { _ -> checkIsValidForm() })
        txtPassword.labelTextView(lblPassword, afterTextChange = { _ -> checkIsValidForm() })
        txtPassword.onActionDone { login() }
        // Initial state
        setTypefaceOnFocus(lblUsername, true)
        checkIsValidForm()
        checkVisibility(txtPassword, lblPassword)
        checkVisibility(txtUsername, lblUsername)
    }

    private fun setTypefaceOnFocus(lbl: TextView, hasFocus: Boolean) {
        lbl.typeface = if (hasFocus) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
    }

    private fun checkVisibility(txt: EditText, lbl: TextView) {
        lbl.visibility = if (txt.text.isEmpty()) View.INVISIBLE else View.VISIBLE
    }

    private fun login() {
        toast(getString(R.string.main_signing_in, txtUsername.text.toString()))
    }

    private fun resetViews() {
        txtUsername.setText("")
        txtPassword.setText("")
    }

    private fun checkIsValidForm() {
        btnAccept.isEnabled = txtUsername.text.isNotBlank() && txtPassword.text.isNotBlank()
    }

}