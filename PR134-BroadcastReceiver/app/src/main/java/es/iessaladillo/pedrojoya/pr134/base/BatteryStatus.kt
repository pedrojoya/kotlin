package es.iessaladillo.pedrojoya.pr134.base

import android.content.Intent
import android.os.BatteryManager

@Suppress("MemberVisibilityCanBePrivate", "unused")
class BatteryStatus internal constructor(intent: Intent) {

    val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
    val isLoading: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
    val plugged: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
    val isPluggedToUsb: Boolean = plugged == BatteryManager.BATTERY_PLUGGED_USB
    val isPluggedToAc: Boolean = plugged == BatteryManager.BATTERY_PLUGGED_AC
    val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
    val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
    val health: Int = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)
    val porcentage: Float = level / scale.toFloat()

    fun hasColdHealth(): Boolean {
        return health == BatteryManager.BATTERY_HEALTH_COLD
    }

    fun hasDeadHealth(): Boolean {
        return health == BatteryManager.BATTERY_HEALTH_DEAD
    }

    fun hasGoodHealth(): Boolean {
        return health == BatteryManager.BATTERY_HEALTH_GOOD
    }

    fun hasOverheatHealth(): Boolean {
        return health == BatteryManager.BATTERY_HEALTH_OVERHEAT
    }

    fun hasOverVoltageHealth(): Boolean {
        return health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE
    }

    fun hasUnspecifiedFailureHealth(): Boolean {
        return health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE
    }

    fun hasUnknownHealth(): Boolean {
        return health == BatteryManager.BATTERY_HEALTH_UNKNOWN
    }

}
