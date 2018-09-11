package es.iessaladillo.pedrojoya.pr057.ui.main

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val subjects: List<String> by lazy {
        arrayListOf("Android", "Acceso a datos", "Libre configuración")
    }

}