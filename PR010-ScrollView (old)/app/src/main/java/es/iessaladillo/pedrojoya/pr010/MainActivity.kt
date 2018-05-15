package es.iessaladillo.pedrojoya.pr010

import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import es.iessaladillo.pedrojoya.pr010.utils.afterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var dateFormatter: SimpleDateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        dateFormatter = SimpleDateFormat("HH:mm:ss",
                Locale.getDefault())

    }

    private fun initViews() {
        txtMessage.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendMessage(txtMessage.text.toString())
                true
            }
            false
        }
        txtMessage.afterTextChanged { checkIsValidForm() }
        btnSend.setOnClickListener { sendMessage(txtMessage.text.toString()) }
        // Initial state.
        txtMessage.setHorizontallyScrolling(false)
        txtMessage.maxLines = resources.getInteger(R.integer.main_activity_txtMensaje_maxLines)
        txtMessage.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
        checkIsValidForm()
        doScroll(scvText, View.FOCUS_DOWN)
    }

    private fun sendMessage(text: String) {
        if (!TextUtils.isEmpty(text)) {
            val time = dateFormatter.format(Date())
            lblText.append(getString(R.string.main_activity_log_message, time, text))
            txtMessage.setText("")
            doScroll(scvText, View.FOCUS_DOWN)
        }
    }

    private fun doScroll(scv: NestedScrollView?, focus: Int) {
        scv?.post {
            scv.fullScroll(focus)
            // scv.scrollTo(0, scv.getBottom());
            txtMessage.requestFocus()
        }
    }

    private fun checkIsValidForm() {
        btnSend.isEnabled = txtMessage.text.toString().isNotEmpty()
    }

}
