package es.iessaladillo.pedrojoya.pr123.ui.main

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModelFactory(@field:IdRes private val defaultEffectResId: Int) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainActivityViewModel(defaultEffectResId) as T

}
