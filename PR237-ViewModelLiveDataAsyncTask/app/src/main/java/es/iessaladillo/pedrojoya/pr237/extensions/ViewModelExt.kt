@file:JvmName("ViewModelExt")

package es.iessaladillo.pedrojoya.pr237.extensions

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.viewModelProvider() =
        lazy {
            ViewModelProviders.of(this).get(T::class.java)
        }