package es.iessaladillo.pedrojoya.pr002

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import es.iessaladillo.pedrojoya.pr002.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chkPolite.setOnCheckedChangeListener { _, checked -> showPoliteMode(checked) }
        btnGreet.setOnClickListener { greet() }
    }

    private fun greet() {
        hideKeyboard(btnGreet)
        val message = "${getString(R.string.main_activity_good_morning)
        }${if (chkPolite.isChecked) getString(R.string
                .main_activity_nice_to_meet_you) else ""} ${txtName.text}"
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun showPoliteMode(checked: Boolean) {
        chkPolite.setText(if (checked) getString(R.string
                .main_activity_polite_mode) else getString(
                R.string.main_activity_impolite_mode))
    }
}
