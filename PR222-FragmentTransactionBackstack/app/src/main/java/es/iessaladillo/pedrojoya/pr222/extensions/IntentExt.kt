@file:JvmName("IntentExt")

package es.iessaladillo.pedrojoya.pr222.extensions

import android.app.Activity
import androidx.annotation.StringRes

fun Activity.extraString(extraId: String, @StringRes defaultStringResId: Int) =
    lazy { intent?.getStringExtra(extraId) ?: getString(defaultStringResId) }

