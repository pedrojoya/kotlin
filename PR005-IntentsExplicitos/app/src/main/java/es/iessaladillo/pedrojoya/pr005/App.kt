package es.iessaladillo.pedrojoya.pr005

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}
