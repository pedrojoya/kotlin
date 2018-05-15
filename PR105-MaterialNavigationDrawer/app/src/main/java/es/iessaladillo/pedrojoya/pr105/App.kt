package es.iessaladillo.pedrojoya.pr105

import android.app.Application

import com.mooveit.library.Fakeit

@Suppress("UNUSED")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fakeit.init()
    }

}
