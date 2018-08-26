package es.iessaladillo.pedrojoya.pr242.services

import android.app.IntentService
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import es.iessaladillo.pedrojoya.pr242.BuildConfig
import es.iessaladillo.pedrojoya.pr242.CHANNEL_ID
import es.iessaladillo.pedrojoya.pr242.R
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*

private const val EXTRA_DATA = "EXTRA_DATA"
const val EXTRA_FILENAME = "EXTRA_FILENAME"
const val ACTION_EXPORTED = BuildConfig.APPLICATION_ID + ".ACTION_EXPORTED"
private const val NOTIFICATION_ID = 100

class ExportToTextFileService : IntentService("ExportToTextFileService") {

    private lateinit var builder: NotificationCompat.Builder

    override fun onCreate() {
        super.onCreate()
        startForeground(NOTIFICATION_ID, buildForegroundNotification())
    }

    override fun onHandleIntent(intent: Intent?) {
        intent?.run {
            try {
                val outputFile = createFile()
                writeListToFile(outputFile, getStringArrayListExtra(EXTRA_DATA))
                sendResult(outputFile)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        stopForeground(true)
    }

    private fun buildForegroundNotification(): Notification {
        builder = NotificationCompat.Builder(this, CHANNEL_ID).setOngoing(true)
                .setSmallIcon(R.drawable.ic_file_download_black_24dp)
                .setTicker(getString(R.string.export_service_exporting))
                .setContentTitle(getString(R.string.export_service_exporting))
                .setContentText(getString(R.string.export_service_progress, 0, 10))
                .setProgress(10, 0, false)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
        return builder.build()
    }

    private fun sendResult(outputFile: File) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
                Intent(ACTION_EXPORTED).putExtra(EXTRA_FILENAME, Uri.fromFile(outputFile))
        )
    }

    @Throws(FileNotFoundException::class, InterruptedException::class)
    private fun writeListToFile(outputFile: File, items: List<String>) {
        val printWriter = PrintWriter(outputFile)
        for (i in items.indices) {
            Thread.sleep(1000)
            updateForegroundNotification(i + 1, items.size)
            printWriter.println(items[i])
        }
        printWriter.close()
    }

    private fun updateForegroundNotification(current: Int, max: Int) {
        builder.setProgress(max, current, false).setContentText(
                getString(R.string.export_service_progress, current, max))
        startForeground(NOTIFICATION_ID, builder.build())
    }

    private fun createFile(): File {
        val baseName = "students"
        val externalStorageState = Environment.getExternalStorageState()
        val rootDir: File
        rootDir = if (externalStorageState == Environment.MEDIA_MOUNTED) {
            Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS)
        } else {
            filesDir
        }
        rootDir.mkdirs()
        val simpleDataFormat = SimpleDateFormat("yyyyMMddHHmm",
                Locale.getDefault())
        val filename = baseName + simpleDataFormat.format(Date()) + ".txt"
        return File(rootDir, filename)
    }

    companion object {

        fun start(context: Context, data: ArrayList<String>) {
            ContextCompat.startForegroundService(context,
                    Intent(context, ExportToTextFileService::class.java)
                            .putExtra(EXTRA_DATA, data)
            )
        }

    }

}
