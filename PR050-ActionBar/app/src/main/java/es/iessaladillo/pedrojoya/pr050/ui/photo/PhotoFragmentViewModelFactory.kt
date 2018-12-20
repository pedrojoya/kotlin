package es.iessaladillo.pedrojoya.pr050.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhotoFragmentViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PhotoFragmentViewModel() as T

}