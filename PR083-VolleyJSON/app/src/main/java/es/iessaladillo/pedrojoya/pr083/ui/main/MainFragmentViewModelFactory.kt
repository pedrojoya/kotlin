package es.iessaladillo.pedrojoya.pr083.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue

class MainFragmentViewModelFactory(private val requestQueue: RequestQueue) : ViewModelProvider
.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainFragmentViewModel(requestQueue) as T

}