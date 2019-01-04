package es.iessaladillo.pedrojoya.pr129.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr129.data.ClockLiveData

class MainFragmentViewModel : ViewModel() {

    val clock = ClockLiveData()
    private val running = MutableLiveData<Boolean>()

    init {
        running.value = false
    }

    fun getRunning(): LiveData<Boolean> {
        return running
    }

    fun startOrStop() {
        if (running.value != null && running.value!!) {
            running.value = false
            clock.stop()
        } else {
            running.value = true
            clock.start()
        }
    }

    override fun onCleared() {
        clock.stop()
        super.onCleared()
    }

}
