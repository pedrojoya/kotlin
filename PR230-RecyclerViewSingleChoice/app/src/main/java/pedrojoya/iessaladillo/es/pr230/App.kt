package pedrojoya.iessaladillo.es.pr230

import android.app.Application

import com.mooveit.library.Fakeit
import com.squareup.leakcanary.LeakCanary

@Suppress("unused")
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
