package es.iessaladillo.pedrojoya.pr149.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import es.iessaladillo.pedrojoya.pr149.R
import es.iessaladillo.pedrojoya.pr149.extensions.*
import kotlinx.android.synthetic.main.activity_main_content.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        txtName.doAfterTextChanged { checkIsValidName() }
        txtPhone.doAfterTextChanged { checkIsValidPhone() }
        txtEmail.doAfterTextChanged { checkIsValidEmail() }
        txtPassword.doAfterTextChanged { checkIsValidPassword() }
        txtPassword.doOnImeAction { signUp() }
        btnSignUp.setOnClickListener { signUp() }
        btnReset.setOnClickListener { reset() }
    }

    private fun signUp() {
        hideSoftKeyboard()
        checkIsValidForm()
        if (isValidForm()) {
            toast(getString(R.string.main_signing_up))
        }
    }

    private fun isValidForm(): Boolean =
            isValidName() && isValidPhone() && isValidEmail() && isValidPassword()

    private fun reset() {
        txtName.setText("")
        txtPhone.setText("")
        txtEmail.setText("")
        txtPassword.setText("")
    }

    private fun isValidName(): Boolean = txtName.text != null && txtName.text.toString().isNotBlank()

    private fun isValidPhone(): Boolean = txtPhone.text != null && txtPhone.text.toString().isValidSpanishPhoneNumber()

    private fun isValidEmail(): Boolean = txtEmail.text != null && txtEmail.text.toString().isValidEmail()

    private fun isValidPassword(): Boolean = txtPassword.text != null && txtPassword.text.toString().isNotBlank()

    private fun checkIsValidForm() {
        checkIsValidName()
        checkIsValidPhone()
        checkIsValidEmail()
        checkIsValidPassword()
    }

    private fun checkIsValidName() {
        tilName.error = if (!isValidName()) getString(R.string.main_required_field) else null
    }

    private fun checkIsValidPhone() {
        tilPhone.error = if (!isValidPhone()) getString(R.string.main_invalid_phone) else null
    }

    private fun checkIsValidEmail() {
        tilEmail.error = if (!isValidEmail()) getString(R.string.main_invalid_email) else null
    }

    private fun checkIsValidPassword() {
        tilPassword.error = if (!isValidPassword()) getString(R.string.main_required_field) else null
    }

}
