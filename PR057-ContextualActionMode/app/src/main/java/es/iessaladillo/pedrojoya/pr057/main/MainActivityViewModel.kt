package es.iessaladillo.pedrojoya.pr057.main

import android.arch.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    internal val subjects: List<String> by lazy {
        arrayListOf("Android", "Acceso a datos", "Libre configuración")
    }

}
