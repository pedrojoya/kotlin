package es.iessaldillo.pedrojoya.pr160.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageFragmentViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            PageFragmentViewModel() as T

}