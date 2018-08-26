package es.iessaladillo.pedrojoya.pr242

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.graphics.Color
import androidx.core.content.getSystemService

const val CHANNEL_ID = "foreground_service"

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    private fun createChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            getSystemService<NotificationManager>()?.createNotificationChannel(
                    NotificationChannel(CHANNEL_ID,
                            "Foreground service", IMPORTANCE_DEFAULT).apply {
                        enableLights(true)
                        lightColor = Color.RED
                        enableVibration(true)
                        vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                    }
            )
        }
    }

}