package es.iessaladillo.pedrojoya.pr002

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        Toast.makeText(this, StringBuilder()
                .append(getString(R.string.main_activity_good_morning))
                .append(if (chkPolite.isChecked) getString(R.string
                        .main_activity_nice_to_meet_you) else "")
                .append(" ")
                .append(txtName.text).toString(), Toast.LENGTH_LONG).show()
    }

    private fun changeText(isChecked: Boolean) {
        chkPolite.text = getString(
                if (isChecked) R.string.main_activity_polite_mode
                else R.string.main_activity_impolite_mode
        )
    }

}