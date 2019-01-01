package es.iessaladillo.pedrojoya.pr092.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Database {

    private val logs = ArrayList<String>()
    private val logsLiveData = MutableLiveData<List<String>>()

    init {
        updateLiveDatas()
    }

    private fun updateLiveDatas() {
        logsLiveData.postValue(ArrayList(logs))
    }

    fun queryLogs(): LiveData<List<String>> {
        return logsLiveData
    }

    fun insertLog(log: String) {
        logs.add(log)
        updateLiveDatas()
    }

}
