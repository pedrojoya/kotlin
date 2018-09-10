package es.iessaladillo.pedrojoya.pr129.ui.main

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr129.data.remote.ClockLiveData

class MainActivityViewModel : ViewModel() {

    val clockLiveData = ClockLiveData()

    fun isClockRunning(): Boolean = clockLiveData.isRunning()

    override fun onCleared() {
        clockLiveData.stop()
        super.onCleared()
    }

    fun start() {
        clockLiveData.start()
    }

    fun stop() {
        clockLiveData.stop()
    }

}
