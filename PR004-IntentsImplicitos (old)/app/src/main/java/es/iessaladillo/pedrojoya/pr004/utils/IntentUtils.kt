package es.iessaladillo.pedrojoya.pr004.utils

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings

fun PackageManager.isActivityAvailable(intent: Intent) =
        this.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size > 0

fun isActivityAvailable(context: Context, intent: Intent) =
        context.applicationContext.packageManager.isActivityAvailable(intent)

fun Activity.startInstalledAppDetailsActivity() {
    val intent = Intent()
    with(intent) {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        addCategory(Intent.CATEGORY_DEFAULT)
        data = Uri.parse("package:${this@startInstalledAppDetailsActivity.packageName}")
        // No track in the stack of activities.
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    }
    this.startActivity(intent)
}

fun newViewUriIntent(uri: Uri): Intent = Intent(Intent.ACTION_VIEW, uri)

fun newWebSearchIntent(text: String): Intent {
    val intent = Intent(Intent.ACTION_WEB_SEARCH)
    intent.putExtra(SearchManager.QUERY, text)
    return intent
}

fun newDialIntent(phoneNumber: String): Intent =
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.trim()))

fun newShowInMapIntent(longit: Double, lat: Double, zoom: Int): Intent =
        Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:$longit,$lat?z=$zoom"))

fun newSearchInMapIntent(text: String): Intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$text"))

fun newContactsIntent(): Intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"))

fun newCallIntent(phoneNumber: String): Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
