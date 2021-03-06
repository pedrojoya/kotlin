@file:JvmName("ViewModelExt")

package es.iessaladillo.pedrojoya.pr082.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline creator: () -> T): T {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
@Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return creator() as T
        }
    }).get(T::class.java)
}