package es.iessaladillo.pedrojoya.pr212

import android.app.Application

import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupLeakCanary()
        setupStetho()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

}
