package es.iessaladillo.pedrojoya.pr237.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

class MainActivityViewModel : ViewModel() {

    private var lastTask: MainActivityLiveData? = null
    private val numSteps: MutableLiveData<Int> = MutableLiveData()
    val task: LiveData<Int> = numSteps.switchMap { steps ->
        MainActivityLiveData(steps).also { lastTask = it }
    }

    val isWorking: Boolean
        get() = lastTask?.isWorking?:false

    fun startWorking(steps: Int) {
        numSteps.value = steps
    }

    fun cancelWorking() {
        lastTask?.cancel()
    }

    override fun onCleared() {
        cancelWorking()
        super.onCleared()
    }

}
