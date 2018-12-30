package es.iessaladillo.pedrojoya.pr050.ui.photo

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhotoFragmentViewModel(@IdRes defaultEffectId: Int) : ViewModel() {

    private val _effectId: MutableLiveData<Int> = MutableLiveData()
    val effectId: LiveData<Int>
        get() = _effectId

    init {
        _effectId.postValue(defaultEffectId)
    }

    fun changeEffect(effectResId: Int) {
        _effectId.postValue(effectResId)
    }

}
