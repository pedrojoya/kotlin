package es.iessaladillo.pedrojoya.pr129.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import java.text.SimpleDateFormat
import java.util.*

const val TAG = "RELOJ"

class ClockLiveData : LiveData<String>() {

    private val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    private var thread: Thread? = null

    fun isRunning(): Boolean = thread?.isAlive?:false

    fun start() {
        if (thread == null || !thread!!.isAlive)
            thread = Thread {
                while (!Thread.currentThread().isInterrupted) {
                    postValue(simpleDateFormat.format(Date()))
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        // Interrupted while sleeping.
                        Log.d(TAG, "Thread stopped")
                        return@Thread
                    }

                }
                Log.d(TAG, "Thread stopped")
            }
        thread!!.start()
        Log.d(TAG, "Thread started")
    }

    fun stop() {
        if (isRunning()) {
            thread?.interrupt()
            Log.d(TAG, "Thread interrupted")
        }
    }

}
