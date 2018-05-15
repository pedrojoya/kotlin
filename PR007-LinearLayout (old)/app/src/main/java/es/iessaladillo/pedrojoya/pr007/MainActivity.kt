package es.iessaladillo.pedrojoya.pr007

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import es.iessaladillo.pedrojoya.pr007.utils.afterTextChanged
import es.iessaladillo.pedrojoya.pr007.utils.setLabelView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnAccept.setOnClickListener { signIn() }
        btnCancel.setOnClickListener { resetViews() }
        txtUsername.setLabelView(lblUsername)
        txtPassword.setLabelView(lblPassword)
        txtUsername.afterTextChanged { checkIsValidForm() }
        txtPassword.afterTextChanged { checkIsValidForm() }
        // Initial state.
        lblUsername.typeface = Typeface.DEFAULT_BOLD
        lblUsername.visibility = View.INVISIBLE
        lblPassword.visibility = View.INVISIBLE
        checkIsValidForm()
    }

    private fun signIn() {
        Toast.makeText(this,
                getString(R.string.main_activity_signing_in, txtUsername.text.toString()),
                Toast.LENGTH_SHORT).show()
    }
    private fun resetViews() {
        txtUsername.setText("")
        txtPassword.setText("")
    }

    private fun checkIsValidForm() {
        btnAccept.isEnabled = isValidForm()
    }

    private fun isValidForm() = txtUsername.text.toString().isNotEmpty() && txtPassword.text.isNotEmpty()

}
