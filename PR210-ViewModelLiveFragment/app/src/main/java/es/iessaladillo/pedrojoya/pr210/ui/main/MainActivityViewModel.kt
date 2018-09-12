package es.iessaladillo.pedrojoya.pr210.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr210.data.Database
import es.iessaladillo.pedrojoya.pr210.ui.detail.DetailFragmentBaseViewModel

private const val STATE_ITEM = "STATE_ITEM"

class MainActivityViewModel(database: Database) : DetailFragmentBaseViewModel() {

    var items: List<String> = database.items
    private val _currentItem = MutableLiveData<String>()
    override val currentItem: LiveData<String>
        get() = _currentItem


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
