package es.iessaladillo.pedrojoya.pr239.data.remote

import androidx.lifecycle.LiveData

class StockLiveData : LiveData<Int>() {

    private val stockManager = StockManager()
    private val listener = object: StockManager.Listener {
        override fun onPriceChanged(price: Int) {
            postValue(price)
        }
    }
    private var stopped = true

    override fun onActive() {
        super.onActive()
        if (!stopped) stockManager.addListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        if (!stopped) {
            stockManager.removeListener(listener)
        }
    }

    fun stopListener() {
        if (!stopped) {
            stockManager.removeListener(listener)
            stopped = true
        }
    }

    fun addListener() {
        if (stopped) {
            stockManager.addListener(listener)
            stopped = false
        }
    }

}
