package es.iessaladillo.pedrojoya.pr018.ui.main

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel

class MainFragmentViewModel(@param:IdRes var effectId: Int) : ViewModel() {

    var isVisible = true
        private set

    fun toggleVisibility() {
        isVisible = !isVisible
    }

}
