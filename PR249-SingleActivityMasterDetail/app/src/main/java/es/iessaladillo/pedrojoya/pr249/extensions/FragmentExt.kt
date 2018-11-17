package es.iessaladillo.pedrojoya.pr249.extensions

import androidx.fragment.app.Fragment

fun Fragment.extraString(extraId: String) =
        lazy {
            arguments?.getString(extraId) ?: throw RuntimeException("Fragment requires an argument")
        }