package es.iessaladillo.pedrojoya.pr092.ui.main

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val SIMULATION_SLEEP_MILI: Long = 2000

class MainFragmentViewModel : ViewModel() {

    private val _data: ArrayList<String> = arrayListOf()
    private val _dataLiveData = MutableLiveData<List<String>>()
    val data: LiveData<List<String>> = _dataLiveData
    private var task: LoadData? = null

    init {
        refresh()
    }

    fun refresh() {
        task = LoadData()
        task?.execute()
    }

    override fun onCleared() {
        super.onCleared()
        task?.cancel(true)
    }

    @SuppressLint("StaticFieldLeak")
    inner class LoadData: AsyncTask<Void, Void, String>() {

        private val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        override fun doInBackground(vararg params: Void?): String {
            // Loading time simulation.
            Thread.sleep(SIMULATION_SLEEP_MILI)
            return simpleDateFormat.format(Date())
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            _data.add(result!!)
            _dataLiveData.value = ArrayList<String>(_data)
        }

    }

}
