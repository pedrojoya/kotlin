package es.iessaladillo.pedrojoya.pr241.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.core.app.JobIntentService
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private const val EXTRA_DATA = "EXTRA_DATA"
const val EXTRA_FILENAME = "EXTRA_FILENAME"
const val ACTION_EXPORTED = "es.iessaladillo.pedrojoya.pr100.ACTION_EXPORTED"
private const val JOB_ID = 1000

class ExportToTextFileService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        try {
            TimeUnit.SECONDS.sleep(5)
            val outputFile = createFile()
            writeListToFile(outputFile, intent.getStringArrayListExtra(EXTRA_DATA))
            sendResult(outputFile)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun sendResult(outputFile: File) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(
                Intent(ACTION_EXPORTED).apply {
                    putExtra(EXTRA_FILENAME, Uri.fromFile(outputFile))
                })
    }

    @Throws(FileNotFoundException::class)
    private fun writeListToFile(outputFile: File, items: List<String>) {
        val printWriter = PrintWriter(outputFile)
        for (item in items) {
            printWriter.println(item)
        }
        printWriter.close()
    }

    private fun createFile(): File {
        val baseName = "students"
        val externalStorageState = Environment.getExternalStorageState()
        val rootDir = if (externalStorageState == Environment.MEDIA_MOUNTED) {
            Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS)
        } else {
            filesDir
        }
        rootDir.mkdirs()
        val simpleDataFormat = SimpleDateFormat(
                "yyyyMMddHHmm", Locale.getDefault())
        val filename = (baseName
                + simpleDataFormat.format(Date()) + ".txt")
        return File(rootDir, filename)
    }

    companion object {

        fun start(context: Context, data: ArrayList<String>) {
            val intent = Intent(context, ExportToTextFileService::class.java)
            intent.putExtra(EXTRA_DATA, data)
            JobIntentService.enqueueWork(context, ExportToTextFileService::class.java, JOB_ID, intent)
        }

    }

}
