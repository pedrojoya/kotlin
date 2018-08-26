package es.iessaladillo.pedrojoya.pr096

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

const val DEFAULT_CHANNEL_ID = "Default notification channel"
const val HIGH_PRIORITY_CHANNEL_ID = "High priority notification channel"

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(
                    Context.NOTIFICATION_SERVICE) as NotificationManager
            // Normal channel.
            val defaultChannel = NotificationChannel(DEFAULT_CHANNEL_ID,
                    getString(R.string.default_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT)
            defaultChannel.description = getString(R.string.default_channel_name)

            notificationManager.createNotificationChannel(defaultChannel)
            // High priority channel.
            val highPriorityChannel = NotificationChannel(HIGH_PRIORITY_CHANNEL_ID,
                    getString(R.string.high_priority_channel_name),
                    NotificationManager.IMPORTANCE_HIGH)
            defaultChannel.description = getString(R.string.high_priority_channel_name)
            notificationManager.createNotificationChannel(highPriorityChannel)
        }
    }

}
