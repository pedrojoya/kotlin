package es.iessaladillo.pedrojoya.pr014.components

import android.view.View
import android.widget.ProgressBar

interface ProgressManager {
    fun showIndeterminateProgress()
    fun hideIndeterminateProgress()
}

class ProgressBarProgressManager(val progressBar: ProgressBar) : ProgressManager {

    override fun showIndeterminateProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideIndeterminateProgress() {
        progressBar.visibility = View.INVISIBLE
    }

}