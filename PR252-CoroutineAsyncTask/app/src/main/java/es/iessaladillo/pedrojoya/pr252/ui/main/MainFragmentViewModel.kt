package es.iessaladillo.pedrojoya.pr252.ui.main

import android.util.Log
import androidx.lifecycle.*
import es.iessaladillo.pedrojoya.pr252.base.Event
import es.iessaladillo.pedrojoya.pr252.base.Resource
import kotlinx.coroutines.*

private const val TAG: String = "MainFragmentViewModel"

class MainFragmentViewModel : ViewModel() {

    private val workingTaskTrigger: MutableLiveData<Int> = MutableLiveData()
    val workingTask: LiveData<Resource<Event<String>>>
    private var lastWorkingTask: Job? = null

    init {
        workingTask = Transformations.switchMap(workingTaskTrigger) { steps ->
            doWork(steps)
        }
    }


    fun startWorking(steps: Int) {
        workingTaskTrigger.value = steps
    }

    private fun doWork(steps: Int): LiveData<Resource<Event<String>>> {
        val result: MutableLiveData<Resource<Event<String>>> = MutableLiveData()
        lastWorkingTask = viewModelScope.launch {
            result.value = Resource.loading(0)
            Log.d(TAG, "Inicialización en ${Thread.currentThread().name}")
            var i = 0
            while (i < steps && isActive) {
                withContext(Dispatchers.IO) {
                    try {
                        delay(1000)
                    } catch (e: Exception) {
                        Log.d(TAG, "Task cancelled by user")
                        result.postValue(Resource.error(Exception("Task cancelled by user")))
                    }
                    Log.d(TAG, "Paso ${i + 1} en ${Thread.currentThread().name}")
                }
                result.value = Resource.loading(i + 1)
                Log.d(TAG, "Guardar paso ${i + 1} en ${Thread.currentThread().name}")
                i++
            }
            if (!isActive) {
                Log.d(TAG, "Task cancelled by user")
                result.setValue(Resource.error(Exception("Task cancelled by user")))
            } else {
                Log.d(TAG, "Task finished successfully")
                result.setValue(Resource.success(Event("Task completed successfully")))
            }
        }
        return result
    }

    fun cancelWorking() {
        lastWorkingTask?.cancel()
    }

    override fun onCleared() {
        viewModelScope.coroutineContext.cancel()
        super.onCleared()
    }

}
