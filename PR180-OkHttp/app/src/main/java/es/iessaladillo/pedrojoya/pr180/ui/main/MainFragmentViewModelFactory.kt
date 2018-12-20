package es.iessaladillo.pedrojoya.pr180.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient

class MainFragmentViewModelFactory(private val okHttpClient: OkHttpClient) : ViewModelProvider
.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainFragmentViewModel(okHttpClient) as T

}