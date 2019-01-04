package es.iessaladillo.pedrojoya.pr134.base

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

import androidx.lifecycle.LiveData

class BatteryStatusLiveData private constructor(private val application: Application) : LiveData<BatteryStatus>() {

    private val batteryBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                postValue(BatteryStatus(intent))
            }
        }
    }
    private val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)

    override fun onActive() {
        super.onActive()
        application.registerReceiver(batteryBroadcastReceiver, intentFilter)
    }

    override fun onInactive() {
        application.unregisterReceiver(batteryBroadcastReceiver)
        super.onInactive()
    }

    companion object : SingletonHolder<BatteryStatusLiveData, Application>(::BatteryStatusLiveData)

}
