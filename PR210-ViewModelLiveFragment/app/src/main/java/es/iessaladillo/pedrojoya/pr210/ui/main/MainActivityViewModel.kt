package es.iessaladillo.pedrojoya.pr210.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr210.data.Database
import es.iessaladillo.pedrojoya.pr210.ui.detail.DetailFragmentBaseViewModel

private const val STATE_ITEM = "STATE_ITEM"

class MainActivityViewModel(val database: Database) : DetailFragmentBaseViewModel() {

    private var items: List<String> = Database.items
    private val _currentItem = MutableLiveData<String>()

    override fun getCurrentItem(): LiveData<String> {
        return _currentItem
    }

    override fun setCurrentItem(item: String) {
        _currentItem.value = item
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_ITEM, _currentItem.value)
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (_currentItem.value == null) {
            _currentItem.value = savedInstanceState.getString(STATE_ITEM)
        }
    }

}
