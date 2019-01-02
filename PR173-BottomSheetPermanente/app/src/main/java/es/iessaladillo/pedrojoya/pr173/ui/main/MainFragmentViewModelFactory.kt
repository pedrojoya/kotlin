package es.iessaladillo.pedrojoya.pr173.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainFragmentViewModelFactory(private val bsbInitialState: Int) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            return MainFragmentViewModel(bsbInitialState) as T
        }
        throw IllegalArgumentException(
                "Cannot instantiate ViewModel class with these arguments")
    }

}
