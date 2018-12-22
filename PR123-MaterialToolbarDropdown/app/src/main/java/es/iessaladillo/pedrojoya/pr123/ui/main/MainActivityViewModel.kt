package es.iessaladillo.pedrojoya.pr123.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// We must use activity's viewModel and NOT FRAGMENTS VIEWMODELS, in order to preserve
// values during spinner selection change.
class MainActivityViewModel(defaultEffectResId: Int) : ViewModel() {

    private val likesLiveData = MutableLiveData<Int>()
    private val effectResIdLiveData = MutableLiveData<Int>()

    val likes: LiveData<Int>
        get() = likesLiveData

    val effectResId: LiveData<Int>
        get() = effectResIdLiveData

    init {
        likesLiveData.postValue(0)
        effectResIdLiveData.postValue(defaultEffectResId)
    }

    fun incrementLikes() {
        likesLiveData.postValue((likesLiveData.value ?: 0) + 1)
    }

    fun setEffectResId(effectResId: Int) {
        effectResIdLiveData.postValue(effectResId)
    }

}
