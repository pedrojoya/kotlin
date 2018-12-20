package es.iessaladillo.pedrojoya.pr018.ui.main

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainFragmentViewModelFactory(@IdRes val defaultEffectMenuResId: Int) : ViewModelProvider
.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainFragmentViewModel(defaultEffectMenuResId) as T

}