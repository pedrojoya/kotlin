@file:JvmName("IntentExt")
package es.iessaladillo.pedrojoya.pr210.extensions

import android.app.Activity

fun Activity.extraString(extraId: String, default: String = "") =
        lazy { intent?.getStringExtra(extraId) ?: default }
