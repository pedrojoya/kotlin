@file:JvmName("ViewModelExt")

package es.iessaldillo.pedrojoya.pr160.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> Fragment.viewModelProvider() =
        lazy {
            ViewModelProviders.of(this).get(T::class.java)
        }