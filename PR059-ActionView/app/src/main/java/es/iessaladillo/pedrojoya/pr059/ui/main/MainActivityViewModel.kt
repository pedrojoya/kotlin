package es.iessaladillo.pedrojoya.pr059.ui.main

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var searchQuery: String = ""
    var isSearchViewExpanded = false
    val students = arrayListOf("Baldomero", "Gervasio", "Filomeno", "Prudencio",
            "Casimiro", "Fulgencio", "Genaro", "Rigoberto", "Atanasio")

}
