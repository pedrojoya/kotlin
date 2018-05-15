@file:JvmName("AdapterViewExt")

package es.iessaladillo.pedrojoya.pr021.extensions

import android.view.View
import android.widget.AdapterView


inline fun AdapterView<*>.onItemSelected(crossinline action: (AdapterView<*>, View?, Int, Long) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, id: Long) {
            action(adapterView, view, position, id)
        }

        override fun onNothingSelected(adapterView: AdapterView<*>) {

        }
    }
}
