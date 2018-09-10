package es.iessaladillo.pedrojoya.pr022.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.muddzdev.styleabletoastlibrary.StyleableToast
import com.tapadoo.alerter.Alerter
import es.iessaladillo.pedrojoya.pr022.R
import es.iessaladillo.pedrojoya.pr022.extensions.snackbar
import es.iessaladillo.pedrojoya.pr022.extensions.toast
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
        btnAlerter.setOnClickListener { showAlerter() }
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
                .iconStart(R.drawable.ic_add_alert)
                .show()
    }

    private fun showToast(message: String) {
        toast(message)
    }

    // Shows a toast that uses an specific layout. Receives the layout resId and the textView
    // resId inside the layout.
    private fun showToastLayout(@StringRes stringResId: Int, @LayoutRes layoutId: Int,
                                @IdRes textViewId: Int) {
        val root = LayoutInflater.from(this).inflate(layoutId, null)
        val textView: TextView = ViewCompat.requireViewById(root, textViewId)
        textView.setText(stringResId)
        Toast(this).apply {
            view = root
            duration = Toast.LENGTH_SHORT
        }.show()
    }

    private fun changeVisibility(view: View) {
        view.visibility = if (view.isVisible) View.INVISIBLE else View.VISIBLE
    }

    private fun showSnackbar(message: String) {
        lblText.snackbar(message, actionText = getString(R.string.main_activity_undo)) {
            changeVisibility(lblText)
        }
    }

}
