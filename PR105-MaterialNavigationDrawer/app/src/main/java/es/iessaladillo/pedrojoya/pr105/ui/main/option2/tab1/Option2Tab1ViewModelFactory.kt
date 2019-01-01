package es.iessaladillo.pedrojoya.pr105.ui.main.option2.tab1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import es.iessaladillo.pedrojoya.pr105.data.Repository

internal class Option2Tab1ViewModelFactory(private val repository: Repository) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Option2Tab1ViewModel(repository) as T
    }

}
