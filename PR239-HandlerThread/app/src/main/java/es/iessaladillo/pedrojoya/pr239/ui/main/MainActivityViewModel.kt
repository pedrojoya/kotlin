package es.iessaladillo.pedrojoya.pr239.ui.main

import androidx.lifecycle.ViewModel

import es.iessaladillo.pedrojoya.pr239.data.remote.StockLiveData

class MainActivityViewModel : ViewModel() {

    val stockLiveData = StockLiveData()

    fun start() {
        stockLiveData.addListener()
    }

    fun stop() {
        stockLiveData.stopListener()
    }

}
