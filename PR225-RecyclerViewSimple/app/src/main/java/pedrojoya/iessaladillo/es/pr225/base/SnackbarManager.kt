package pedrojoya.iessaladillo.es.pr225.base

import android.view.View

import com.google.android.material.snackbar.Snackbar

class SnackbarManager(private val view: View) : MessageManager {

    override fun showMessage(message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

}
