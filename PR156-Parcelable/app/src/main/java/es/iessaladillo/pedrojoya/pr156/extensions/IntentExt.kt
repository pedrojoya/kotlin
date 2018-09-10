@file:JvmName("IntentExt")

package es.iessaladillo.pedrojoya.pr156.extensions

import android.app.Activity
import android.os.Parcelable

fun <T: Parcelable> Activity.extra(extraId: String) =
    lazy { intent?.getParcelableExtra(extraId) as T }
