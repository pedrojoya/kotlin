package es.iessaladillo.pedrojoya.pr022

import android.graphics.Color
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.muddzdev.styleabletoastlibrary.StyleableToast
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnToast.setOnClickListener { showToast(getString(R.string.main_activity_toast_message)) }
        btnToastLayout.setOnClickListener {
            showToastLayout(R.string.main_activity_layout_message, R.layout.toast, R.id.lblMessage)
        }
        btnSnackbar.setOnClickListener {
            changeVisibility(lblText)
            showSnackbar(getString(R.string.main_activity_visibility_changed))
        }
        btnStylableToast.setOnClickListener { showStylableToast() }
        btnAlerter!!.setOnClickListener { showAlerter() }
    }

    private fun showAlerter() {
        Alerter.create(this)
                .setTitle(R.string.main_activity_attention)
                .setText(R.string.main_activity_alerter_message)
                .setBackgroundColorRes(R.color.accent)
                .show()
    }

    private fun showStylableToast() {
        StyleableToast.Builder(this)
                .text(getString(R.string.main_activity_stylable_message))
                .backgroundColor(Color.RED)
                .textColor(Color.WHITE)
                .iconResLeft(R.drawable.ic_add_alert)
                .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        // toast(message)
    }

    // Shows a toast that uses an specific layout. Receives the layout resId and the textView
    // resId inside the layout.
    private fun showToastLayout(@StringRes stringResId: Int, @LayoutRes layoutId: Int,
                                @IdRes textViewId: Int) {
        val root = LayoutInflater.from(this).inflate(layoutId, null)
        val textView: TextView = ViewCompat.requireViewById(root, textViewId)
        textView.setText(stringResId)
        with(Toast(this)) {
            view = root
            duration = Toast.LENGTH_SHORT
            show()
        }
    }

    private fun changeVisibility(view: View) {
        view.visibility = if (view.isVisible) View.INVISIBLE else View.VISIBLE
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(lblText, message, Snackbar.LENGTH_LONG).setAction(
                getString(R.string.main_activity_undo)) { changeVisibility(lblText) }.show()
//        snackbar(lblText, message, getString(R.string.main_activity_undo)) {
//            changeVisibility(lblText)
//        }
    }

}
