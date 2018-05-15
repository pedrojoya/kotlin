@file:JvmName("ArchExt")

package es.iessaladillo.pedrojoya.pr097.extensions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity

inline fun <reified T : ViewModel>FragmentActivity.getViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline creator: () -> T): T {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
@Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return creator() as T
        }
    }).get(T::class.java)
}