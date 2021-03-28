@file:JvmName("IntentExt")

package es.iessaladillo.pedrojoya.pr004.extensions

import android.app.SearchManager
import android.content.ContentResolver.SCHEME_CONTENT
import android.content.Intent
import android.net.Uri
import android.webkit.WebView.SCHEME_GEO
import android.webkit.WebView.SCHEME_TEL

fun newViewUriIntent(uri: Uri) = Intent(Intent.ACTION_VIEW, uri)

fun newWebSearchIntent(text: String): Intent =
        Intent(Intent.ACTION_WEB_SEARCH)
                .putExtra(SearchManager.QUERY, text)

fun newDialIntent(phoneNumber: String) = Intent(Intent.ACTION_DIAL,
        Uri.parse("$SCHEME_TEL${phoneNumber.trim()}"))

fun newShowInMapIntent(longit: Double, lat: Double, zoom: Int) = Intent(Intent.ACTION_VIEW,
        Uri.parse("geo:$longit,$lat?z=$zoom"))

fun newSearchInMapIntent(text: String) = Intent(Intent.ACTION_VIEW,
        Uri.parse("$SCHEME_GEO${text.trim()}"))

fun newContactsIntent() = Intent(Intent.ACTION_VIEW, Uri.parse
("$SCHEME_CONTENT://contacts/people/"))
