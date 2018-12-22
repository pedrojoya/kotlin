package es.iessaladillo.pedrojoya.pr211.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr211.data.Repository

class MainFragmentViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainFragmentViewModel(repository) as T

}