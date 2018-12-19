package es.iessaladillo.pedrojoya.pr057

import android.app.Application

import com.mooveit.library.Fakeit
import com.squareup.leakcanary.LeakCanary

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        Fakeit.init()
    }

}
