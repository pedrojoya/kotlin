package es.iessaladillo.pedrojoya.pr021.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr021.data.local.model.Country
import es.iessaladillo.pedrojoya.pr021.data.Repository

class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    val data: List<Country> by lazy { repository.queryCountries() }

}
