package es.iessaladillo.pedrojoya.pr147.ui.likes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LikesFragmentViewModel : ViewModel() {

    private val _likes: MutableLiveData<Int> = MutableLiveData()
    val likes: LiveData<Int>
        get() = _likes

    init {
        _likes.value = 0
    }

    fun incrementLikes() {
        _likes.value = (_likes.value?:0) + 1
    }

}
