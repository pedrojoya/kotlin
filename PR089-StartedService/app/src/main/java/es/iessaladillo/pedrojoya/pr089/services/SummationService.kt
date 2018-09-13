package es.iessaladillo.pedrojoya.pr089.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

const val TAG = "STARTED_SERVICE"
const val EXTRA_NUMBER = "EXTRA_NUMBER"
const val EXTRA_RESULT = "EXTRA_RESULT"
const val ACTION_SUMMATION = "es.iessaladillo.pedrojoya.pr089.actions.ACTION_SUMMATION"

class SummationService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service created")
    }

    override fun onDestroy() {
        Log.d(TAG, "Service destroyed")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        onHandleIntent(intent)
        // Not sticky service.
        return Service.START_NOT_STICKY
    }

    private fun onHandleIntent(intent: Intent) {
        if (intent.hasExtra(EXTRA_NUMBER)) {
            val number = intent.getLongExtra(EXTRA_NUMBER, 0)
            Thread { calculateSummation(number) }.start()
        }
    }

    private fun calculateSummation(number: Long) {
        sendResult(number, summation(number))
    }

    private fun summation(number: Long): Long {
        var result: Long = 0
        for (i in 1..number) {
            result += i
            try {
                Thread.sleep(50)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return result
    }

    private fun sendResult(number: Long, summation: Long) {
        val intent = Intent(ACTION_SUMMATION)
        intent.putExtra(EXTRA_NUMBER, number)
        intent.putExtra(EXTRA_RESULT, summation)
        if (!LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(
                        Intent(ACTION_SUMMATION)
                        .putExtra(EXTRA_NUMBER, number)
                        .putExtra(EXTRA_RESULT, summation))) {
            Log.d(TAG, "Summation of $number = $summation")
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        // It's a started service, not a bound service.
        return null
    }

    companion object {

        fun start(context: Context, number: Long) {
            val intent = Intent(context, SummationService::class.java)
            intent.putExtra(EXTRA_NUMBER, number)
            context.startService(intent)
        }

    }

}
