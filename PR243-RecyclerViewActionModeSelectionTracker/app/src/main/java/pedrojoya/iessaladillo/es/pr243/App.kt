package pedrojoya.iessaladillo.es.pr243

import android.app.Application

import com.mooveit.library.Fakeit
import com.squareup.leakcanary.LeakCanary

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        Fakeit.init()
    }

}
