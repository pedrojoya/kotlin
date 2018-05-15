@file:JvmName("IntentExt")

package es.iessaladillo.pedrojoya.pr119.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

// Apps settings activity intent.
fun newInstalledAppDetailsActivityIntent(context: Context): Intent =
        Intent()
                .setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .addCategory(Intent.CATEGORY_DEFAULT)
                .setData(Uri.fromParts("package", context.packageName, null))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                .addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

fun newViewUriIntent(uri: Uri, mimeType: String): Intent =
        Intent(Intent.ACTION_VIEW).setDataAndType(uri, mimeType)

