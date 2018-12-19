package es.iessaladillo.pedrojoya.pr010.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doAfterTextChanged
import es.iessaladillo.pedrojoya.pr010.R
import es.iessaladillo.pedrojoya.pr010.extensions.doOnImeAction
import es.iessaladillo.pedrojoya.pr010.extensions.hideSoftKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        checkInitialState()

    }

    private fun checkInitialState() {
        checkIsValidForm()
        doScroll(scvText)
    }

    private fun setupViews() {
        txtMessage.apply {
            doAfterTextChanged { checkIsValidForm() }
            doOnImeAction { sendMessage() }
        }
        btnSend.setOnClickListener { sendMessage() }
    }

    private fun sendMessage() {
        val text = txtMessage.text.toString()
        if (isValidForm()) {
            hideSoftKeyboard()
            lblText.append(getString(R.string.main_log_message, simpleDateFormat.format(Date()), text))
            txtMessage.setText("")
            doScroll(scvText)
        }
    }

    private fun doScroll(scv: NestedScrollView) {
        // Must be posted in order to calculate the end position correctly.
        scv.post {
            scv.smoothScrollTo(0, scv.bottom)
            txtMessage.requestFocus()
        }
    }

    private fun checkIsValidForm() {
        btnSend.isEnabled = isValidForm()
    }

    private fun isValidForm() = txtMessage.text.isNotBlank()

}
