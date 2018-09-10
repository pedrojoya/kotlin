package es.iessaladillo.pedrojoya.pr049.main

import android.os.Bundle
import androidx.lifecycle.ViewModel

private const val STATE_SELECTED_ITEM = "STATE_SELECTED_ITEM"

class MainActivityViewModel(private var defaultItem: String) : ViewModel() {

    var items: List<String> = arrayListOf("Baldomero", "Sergio", "Pablo", "Rodolfo", "Atanasio",
            "Gervasio", "Prudencia", "Oswaldo", "Gumersindo", "Gerardo", "Rodrigo", "Óscar")
        private set

    var selectedItem = defaultItem

    fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_SELECTED_ITEM, selectedItem)
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && selectedItem == defaultItem) {
            selectedItem = savedInstanceState.getString(STATE_SELECTED_ITEM, defaultItem)
        }
    }

}
