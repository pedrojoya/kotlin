package es.iessaldillo.pedrojoya.pr191.ui.main

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _currentOption: MutableLiveData<Int> = MutableLiveData()
    val currentOption: LiveData<Int>
        get() = _currentOption

    fun setOption(@IdRes currentOptionMenuItemResIsd: Int) {
        _currentOption.postValue(currentOptionMenuItemResIsd)
    }

}