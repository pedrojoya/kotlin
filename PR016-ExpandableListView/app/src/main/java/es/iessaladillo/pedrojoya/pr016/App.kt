package es.iessaladillo.pedrojoya.pr016

import android.app.Application

import com.squareup.leakcanary.LeakCanary

@Suppress("UNUSED")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)
    }

}
