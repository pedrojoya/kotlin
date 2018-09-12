package es.iessaladillo.pedrojoya.pr210.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class DetailFragmentBaseViewModel : ViewModel() {

    abstract val currentItem: LiveData<String>

    abstract fun setCurrentItem(item: String)

}
