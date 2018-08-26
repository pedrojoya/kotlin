@file:JvmName("ArchExt")

package es.iessaladillo.pedrojoya.pr057.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.FragmentActivity

inline fun <reified T : ViewModel>FragmentActivity.getViewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}
