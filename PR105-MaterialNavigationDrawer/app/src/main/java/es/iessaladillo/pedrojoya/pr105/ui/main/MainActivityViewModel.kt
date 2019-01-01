package es.iessaladillo.pedrojoya.pr105.ui.main

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _currentOption = MutableLiveData<Int>()
    val currentOption: LiveData<Int>
        get() = _currentOption

    fun setCurrentOption(@IdRes optionMenuItemResId: Int) {
        _currentOption.postValue(optionMenuItemResId)
    }

}
