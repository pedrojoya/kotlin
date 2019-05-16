package es.iessaladillo.pedrojoya.pr002.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr002.R
import es.iessaladillo.pedrojoya.pr002.extensions.hideKeyboard
import es.iessaladillo.pedrojoya.pr002.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        chkPolite.setOnCheckedChangeListener { _, isChecked -> changeText(isChecked) }
        btnGreet.setOnClickListener { greet() }
    }

    private fun greet() {
        hideKeyboard()
        val morning = getString(R.string.main_good_morning)
        val polite = if (chkPolite.isChecked) getString(R.string.main_nice_to_meet_you) else ""
        val name = txtName.text
        val message = "$morning $polite $name"
        toast(message)
    }

    private fun changeText(isChecked: Boolean) {
        chkPolite.text = getString(
                if (isChecked) R.string.main_polite_mode
                else R.string.main_impolite_mode
        )
    }

}