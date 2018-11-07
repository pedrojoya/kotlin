@file:JvmName("IntentExt")

package es.iessaladillo.pedrojoya.pr004.extensions

import android.app.SearchManager
import android.content.ContentResolver.SCHEME_CONTENT
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.webkit.WebView.SCHEME_GEO
import android.webkit.WebView.SCHEME_TEL

fun Intent.isActivityAvailable(context: Context) =
        context.applicationContext.packageManager.queryIntentActivities(this,
                PackageManager.MATCH_DEFAULT_ONLY).size > 0

inline fun Context.startActivityWithCheck(intent: Intent, error: () -> Unit = {}) {
    if (intent.isActivityAvailable(this.applicationContext)) {
        startActivity(intent)
    } else {
        error.invoke()
    }
}

// Apps settings activity intent.
fun newInstalledAppDetailsActivityIntent(context: Context): Intent =
        Intent()
                .setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .addCategory(Intent.CATEGORY_DEFAULT)
                .setData(Uri.fromParts("package", context.packageName, null))
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                .addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

fun newViewUriIntent(uri: Uri) = Intent(Intent.ACTION_VIEW, uri)

fun newWebSearchIntent(text: String): Intent = Intent(Intent.ACTION_WEB_SEARCH)
        .putExtra(SearchManager.QUERY, text)

fun newDialIntent(phoneNumber: String) = Intent(Intent.ACTION_DIAL,
        Uri.parse("$SCHEME_TEL${phoneNumber.trim()}"))

fun newShowInMapIntent(longit: Double, lat: Double, zoom: Int) = Intent(Intent.ACTION_VIEW,
        Uri.parse("geo:$longit,$lat?z=$zoom"))

fun newSearchInMapIntent(text: String) = Intent(Intent.ACTION_VIEW,
        Uri.parse("$SCHEME_GEO${text.trim()}"))

fun newContactsIntent() = Intent(Intent.ACTION_VIEW, Uri.parse
("$SCHEME_CONTENT://contacts/people/"))

fun newCallIntent(phoneNumber: String) = Intent(Intent.ACTION_CALL, Uri.parse("$SCHEME_TEL$phoneNumber"))
