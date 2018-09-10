package es.iessaladillo.pedrojoya.pr015.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr015.data.Repository

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    val data by lazy { repository.queryWords() }

}
