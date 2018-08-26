@file:JvmName("ArchExt")

package es.iessaladillo.pedrojoya.pr011.extensions

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline creator: () -> T): T {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return creator() as T
        }
    }).get(T::class.java)
}