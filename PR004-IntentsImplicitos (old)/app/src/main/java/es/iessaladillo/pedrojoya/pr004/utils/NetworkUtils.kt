package es.iessaladillo.pedrojoya.pr004.utils

import android.content.Context
import android.net.ConnectivityManager

fun ConnectivityManager.isConnectionAvailable() = this.activeNetworkInfo != null && this.activeNetworkInfo.isConnected

fun Context.getConnectivityManager() = this.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun isConnectionAvailable(context: Context) = context.getConnectivityManager().isConnectionAvailable()

