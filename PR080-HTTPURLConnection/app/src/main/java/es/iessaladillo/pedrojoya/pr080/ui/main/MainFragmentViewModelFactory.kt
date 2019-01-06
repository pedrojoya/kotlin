package es.iessaladillo.pedrojoya.pr080.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr080.data.Repository

class MainFragmentViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            MainFragmentViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Wrong viewModelClass")
        }
    }

}
