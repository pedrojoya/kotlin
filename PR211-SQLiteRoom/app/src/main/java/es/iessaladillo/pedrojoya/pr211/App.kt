package es.iessaladillo.pedrojoya.pr211

import android.app.Application
import com.facebook.stetho.Stetho

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

}