package es.iessaladillo.pedrojoya.pr002

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr002.extensions.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        chkPolite.setOnCheckedChangeListener { _, isChecked -> changeText(isChecked) }
        btnGreet.setOnClickListener { greet() }
    }

    private fun greet() {
        hideKeyboard()
        toast(StringBuilder()
                .append(getString(R.string.main_activity_good_morning))
                .append(if (chkPolite.isChecked) getString(R.string
                        .main_activity_nice_to_meet_you) else "")
                .append(" ")
                .append(txtName.text).toString(), Toast.LENGTH_LONG)
    }

    private fun changeText(isChecked: Boolean) {
        chkPolite.text = getString(
                if (isChecked) R.string.main_activity_polite_mode
                else R.string.main_activity_impolite_mode
        )
    }

}