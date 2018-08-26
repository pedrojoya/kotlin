package es.iessaladillo.pedrojoya.pr180

import android.app.Application

import com.facebook.stetho.Stetho

@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupStetho()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }
}
