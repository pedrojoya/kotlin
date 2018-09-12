package es.iessaladillo.pedrojoya.pr210.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DetailActivityViewModel : DetailFragmentBaseViewModel() {

    private val _currentItem = MutableLiveData<String>()

    override val currentItem: LiveData<String>
        get() = _currentItem

    override fun setCurrentItem(item: String) {
        _currentItem.value = item
    }

}
