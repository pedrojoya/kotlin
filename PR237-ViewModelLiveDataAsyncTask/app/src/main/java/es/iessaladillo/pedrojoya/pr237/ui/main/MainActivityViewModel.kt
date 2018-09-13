package es.iessaladillo.pedrojoya.pr237.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var lastTask: MainActivityLiveData? = null
    private val numSteps: MutableLiveData<Int> = MutableLiveData()
    val task: LiveData<Int> = Transformations.switchMap(numSteps) { steps ->
        lastTask = MainActivityLiveData(steps)
        lastTask
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
