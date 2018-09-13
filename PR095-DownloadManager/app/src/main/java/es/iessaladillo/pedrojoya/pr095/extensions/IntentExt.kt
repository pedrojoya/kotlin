@file:JvmName("IntentExt")
package es.iessaladillo.pedrojoya.pr095.extensions

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun newInstalledAppDetailsActivityIntent(context: Context): Intent =
    Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        addCategory(Intent.CATEGORY_DEFAULT)
        data = Uri.parse("package:" + context.packageName)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    }


fun newStandardDownloadsActivityIntent(): Intent =
    Intent(DownloadManager.ACTION_VIEW_DOWNLOADS)
