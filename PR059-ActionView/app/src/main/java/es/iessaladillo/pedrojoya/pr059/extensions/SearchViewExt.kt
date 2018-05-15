@file:JvmName("SearchViewExt")

package es.iessaladillo.pedrojoya.pr059.extensions

import android.support.v7.widget.SearchView

inline fun SearchView.onQueryTextChange(crossinline action: (String) -> Boolean) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String)= false
        override fun onQueryTextChange(query: String): Boolean = action(query)
    })
}