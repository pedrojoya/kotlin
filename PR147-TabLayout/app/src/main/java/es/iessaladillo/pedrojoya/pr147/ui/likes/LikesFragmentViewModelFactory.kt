package es.iessaladillo.pedrojoya.pr147.ui.likes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LikesFragmentViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            LikesFragmentViewModel() as T

}