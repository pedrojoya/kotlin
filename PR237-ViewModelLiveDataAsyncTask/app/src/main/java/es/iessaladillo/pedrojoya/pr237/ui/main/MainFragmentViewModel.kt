package es.iessaladillo.pedrojoya.pr237.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr237.base.Event
import es.iessaladillo.pedrojoya.pr237.base.Resource

class MainFragmentViewModel : ViewModel() {

    private var lastWorkingTask: WorkingTask? = null
    private val workingTaskTrigger: MutableLiveData<Int> = MutableLiveData()
    val workingTask: LiveData<Resource<Event<String>>>

    init {
        workingTask = Transformations.switchMap(workingTaskTrigger) { steps ->
            WorkingTask(steps).also { lastWorkingTask = it }
        }
    }

    fun startWorking(steps: Int) {
        workingTaskTrigger.value = steps
    }

    fun cancelWorking() {
        lastWorkingTask?.cancel(true)
    }

    override fun onCleared() {
        cancelWorking()
        super.onCleared()
    }

}
