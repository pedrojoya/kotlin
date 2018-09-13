package es.iessaladillo.pedrojoya.pr180.extensions

import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment

fun Fragment.hideSoftKeyboard() {
    val imm = requireActivity().getSystemService<InputMethodManager>()
    val view = requireActivity().currentFocus
    if (view != null) {
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
