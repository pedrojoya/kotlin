@file:JvmName("ToastUtils")

package es.iessaladillo.pedrojoya.pr021.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}