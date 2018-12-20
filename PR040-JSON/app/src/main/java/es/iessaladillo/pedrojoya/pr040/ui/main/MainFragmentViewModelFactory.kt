package es.iessaladillo.pedrojoya.pr040.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainFragmentViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainFragmentViewModel() as T

}