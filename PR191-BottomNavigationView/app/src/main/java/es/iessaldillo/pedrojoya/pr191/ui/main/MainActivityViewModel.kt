package es.iessaldillo.pedrojoya.pr191.ui.main

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import es.iessaldillo.pedrojoya.pr191.R

class MainActivityViewModel: ViewModel() {

    @IdRes
    var currentItemId: Int = R.id.mnuCalendar

}