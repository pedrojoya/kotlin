package es.iessaladillo.pedrojoya.pr092.data

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr092.base.Event
import es.iessaladillo.pedrojoya.pr092.base.RequestState
import es.iessaladillo.pedrojoya.pr092.data.local.Database
import java.text.SimpleDateFormat
import java.util.*

private const val SIMULATION_SLEEP_MILI: Long = 6000

class RepositoryImpl(private val database: Database) : Repository {

    private var task: LoadDataAsyncTask? = null

    override fun queryLogs(): LiveData<List<String>> = database.queryLogs()

    override fun refreshLogs(): LiveData<RequestState> {
        val requestStateLiveData = MutableLiveData<RequestState>()
        task = LoadDataAsyncTask(requestStateLiveData)
        task?.execute()
        return requestStateLiveData
    }

    override fun cancelRefresh() {
        task?.cancel(true)
    }


    @SuppressLint("StaticFieldLeak")
    inner class LoadDataAsyncTask(private val requestState: MutableLiveData<RequestState>) : AsyncTask<Void, Void, Void>() {

        private val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        override fun doInBackground(vararg params: Void): Void? {
            requestState.postValue(RequestState.Loading)
            // Loading time simulation.
            try {
                Thread.sleep(SIMULATION_SLEEP_MILI)
            } catch (e: InterruptedException) {
                requestState.postValue(RequestState.Error(Event(e)))
                return null
            }
            val log = simpleDateFormat.format(Date())
            database.insertLog(log)
            requestState.postValue(RequestState.Result<List<String>>(listOf(log)))
            return null
        }

    }

}
