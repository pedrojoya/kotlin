package es.iessaladillo.pedrojoya.pr237.ui.main

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData

internal class MainActivityLiveData(steps: Int?) : MutableLiveData<Int>() {

    private val asyncTask: SecundaryTask = SecundaryTask(this)
    val isWorking: Boolean
        get() = asyncTask.status == AsyncTask.Status.RUNNING

    init {
        asyncTask.execute(steps)
    }

    fun cancel() {
        asyncTask.cancel(false)
    }

    class SecundaryTask(private val mutableLiveData: MutableLiveData<Int>) : AsyncTask<Int, Int, Int>() {

        override fun onPreExecute() {
            mutableLiveData.value = 0
        }

        override fun doInBackground(vararg params: Int?): Int {
            val steps = params[0] ?: 0
            var i = 0
            while (i < steps && !isCancelled) {
                work()
                mutableLiveData.postValue(i + 1)
                i++
            }
            return steps
        }

        override fun onCancelled() {
            mutableLiveData.value = 0
        }

        override fun onPostExecute(result: Int?) {
            mutableLiveData.value = result
        }

        private fun work() {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }

    }

}
