package pedrojoya.iessaladillo.es.pr229

import android.app.Application

import com.mooveit.library.Fakeit

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fakeit.init()
    }

}
