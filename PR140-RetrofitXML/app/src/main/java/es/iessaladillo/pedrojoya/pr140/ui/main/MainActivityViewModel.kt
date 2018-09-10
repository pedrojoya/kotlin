package es.iessaladillo.pedrojoya.pr140.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr140.base.RequestState
import es.iessaladillo.pedrojoya.pr140.data.model.City
import es.iessaladillo.pedrojoya.pr140.data.remote.Api
import es.iessaladillo.pedrojoya.pr140.data.remote.CountingLiveData
import java.util.*

@SuppressLint("StaticFieldLeak")
class MainActivityViewModel(api: Api) : ViewModel() {

    private val countingLiveData: CountingLiveData = CountingLiveData(api)
    val cities: List<City> = ArrayList(
            Arrays.asList(City("Algeciras", "04"), City("Los Barrios", "08"),
                    City("Castellar", "13"), City("Jimena", "21"),
                    City("La Línea", "22"), City("San Roque", "33"),
                    City("Tarifa", "35")))
    var selectedCity = -1

    val counting: LiveData<RequestState>
        get() = countingLiveData

    fun loadEscrutinio(codigoPoblacion: String) {
        countingLiveData.loadData(codigoPoblacion)
    }

    override fun onCleared() {
        countingLiveData.cancel()
        super.onCleared()
    }

}
