package es.iessaladillo.pedrojoya.pr011

import android.app.Application
import android.support.v7.app.AppCompatDelegate

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}