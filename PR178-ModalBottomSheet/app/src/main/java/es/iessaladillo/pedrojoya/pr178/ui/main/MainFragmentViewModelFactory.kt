package es.iessaladillo.pedrojoya.pr178.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import es.iessaladillo.pedrojoya.pr178.data.Repository

class MainFragmentViewModelFactory(private val repository: Repository) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            MainFragmentViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Incorrect ViewModel class")
        }
    }

}
