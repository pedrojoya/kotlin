package es.iessaladillo.pedrojoya.pr018.ui.main

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr018.R

class MainActivityViewModel: ViewModel() {

    @IdRes
    var effectId = R.id.mnuOriginal
    var isPhotoVisible = true

}