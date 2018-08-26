package es.iessaladillo.pedrojoya.pr010

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import es.iessaladillo.pedrojoya.pr010.extensions.afterTextChanged
import es.iessaladillo.pedrojoya.pr010.extensions.isNotBlank
import es.iessaladillo.pedrojoya.pr010.extensions.onActionDone
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val simpleDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("HH:mm:ss",
                Locale.getDefault())
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        txtMessage.apply {
            onActionDone {
                sendMessage(text.toString())
            }
            afterTextChanged { checkIsValidForm() }
        }
        btnSend.setOnClickListener { sendMessage(txtMessage.text.toString()) }
        // Initial state
        checkIsValidForm()
        doScroll(scvText)
    }

    private fun sendMessage(text: String) {
        lblText.append(getString(R.string.main_activity_log_message, simpleDateFormat.format(Date()), text))
        txtMessage.setText("")
        doScroll(scvText)
    }

    private fun doScroll(scv: NestedScrollView) {
        // Must be posted in order to calculate the end position correctly.
        scv.post {
            scv.smoothScrollTo(0, scv.bottom)
            txtMessage.requestFocus()
        }
    }

    private fun checkIsValidForm() {
        btnSend.isEnabled = txtMessage.isNotBlank()
    }

}
