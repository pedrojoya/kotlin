package es.iessaladillo.pedrojoya.pr004.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

fun Context.hasPermission(permissionName: String) =
        ContextCompat.checkSelfPermission(this.applicationContext, permissionName) == PackageManager.PERMISSION_GRANTED

fun AppCompatActivity.canCall() = this.hasPermission(Manifest.permission.CALL_PHONE)

fun AppCompatActivity.requestCallPermission(requestCode: Int) {
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), requestCode)
}
