@file:JvmName("IntentExt")

package es.iessaladillo.pedrojoya.pr152.extensions

import android.app.Activity

fun Activity.extraString(extraId: String, default: String? = "") =
    lazy { intent?.getStringExtra(extraId) ?: default }
