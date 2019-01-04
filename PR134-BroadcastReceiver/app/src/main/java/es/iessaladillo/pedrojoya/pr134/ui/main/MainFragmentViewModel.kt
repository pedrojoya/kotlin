package es.iessaladillo.pedrojoya.pr134.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import es.iessaladillo.pedrojoya.pr134.base.BatteryStatusLiveData

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val batteryStatusLiveData: BatteryStatusLiveData = BatteryStatusLiveData.getInstance(application)

}
