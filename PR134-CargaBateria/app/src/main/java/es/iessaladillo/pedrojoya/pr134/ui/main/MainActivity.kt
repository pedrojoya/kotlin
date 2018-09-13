package es.iessaladillo.pedrojoya.pr134.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.BatteryManager.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr134.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val batteryBroadcastReceiver: BatteryBroadcastReceiver = BatteryBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryBroadcastReceiver)
    }

    private inner class BatteryBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val loading = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
            val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
            val pluggedUsb = plugged == BatteryManager.BATTERY_PLUGGED_USB
            val pluggedAc = plugged == BatteryManager.BATTERY_PLUGGED_AC
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)
            val healthMessage = when (health) {
                BATTERY_HEALTH_COLD -> getString(R.string.main_activity_health_cold)
                BATTERY_HEALTH_DEAD -> getString(R.string.main_activity_health_dead)
                BATTERY_HEALTH_GOOD -> getString(R.string.main_activity_health_good)
                BATTERY_HEALTH_OVERHEAT -> getString(R.string.main_activity_health_overheat)
                BATTERY_HEALTH_OVER_VOLTAGE -> getString(R.string.main_activity_health_over_voltage)
                BATTERY_HEALTH_UNSPECIFIED_FAILURE -> getString(R.string.main_activity_health_unspecified_failure)
                else -> getString(R.string.main_activity_health_unknown)
            }
            val porcentage = level / scale.toFloat()
            val sb = StringBuilder()
            if (loading) {
                sb.append(getString(R.string.main_activity_loading)).append(" ")
                if (pluggedUsb) {
                    sb.append(getString(R.string.main_activity_plugged_usb)).append(" ")
                } else if (pluggedAc) {
                    sb.append(getString(R.string.main_activity_plugged_ac)).append(" ")
                }
            } else {
                sb.append(getString(R.string.activity_main_lblLevel)).append(" ")
            }
            sb.append("(").append(level).append("%)").append(" ").append(healthMessage)
            lblLevel.text = sb.toString()
            pbLevel.progress = level
        }

    }

}
