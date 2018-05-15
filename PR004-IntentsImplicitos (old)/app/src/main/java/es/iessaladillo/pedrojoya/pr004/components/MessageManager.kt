package es.iessaladillo.pedrojoya.pr004.components

import android.util.Log
import android.view.View
import android.widget.Toast

interface MessageManager {

    fun showMessage(refView: View, text: String) {
        Log.d("App", text)
    }

}

class ToastMessageManager : MessageManager {

    override fun showMessage(refView: View, text: String) {
        Toast.makeText(refView.context, text, Toast.LENGTH_SHORT).show()
    }

}
