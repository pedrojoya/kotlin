package es.iessaladillo.pedrojoya.pr097

import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {

    var count = 0
        private set

    fun increment() {
        count++
    }

}