package es.iessaladillo.pedrojoya.pr050.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhotoFragmentViewModelFactory(private val defaultEffectId: Int) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PhotoFragmentViewModel(defaultEffectId) as T

}