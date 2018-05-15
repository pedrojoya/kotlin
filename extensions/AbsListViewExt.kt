@file:JvmName("AbsListViewExt")
package es.iessaladillo.pedrojoya.pr057.extensions

import android.widget.AbsListView
import androidx.core.util.forEach
import java.util.*

@Suppress("UNCHECKED_CAST")
fun <T> AbsListView.getSelectedItems(uncheck: Boolean = false): List<T> =
    ArrayList<T>().apply {
        checkedItemPositions.forEach { key, value ->
            if (value) {
                if (uncheck) {
                    setItemChecked(key, false)
                }
                add(getItemAtPosition(key) as T)
            }
        }
    }


