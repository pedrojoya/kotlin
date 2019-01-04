package es.iessaladillo.pedrojoya.pr211.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr211.data.Repository

class ListFragmentViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            return ListFragmentViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Wrong viewModelClass")
        }
    }

}