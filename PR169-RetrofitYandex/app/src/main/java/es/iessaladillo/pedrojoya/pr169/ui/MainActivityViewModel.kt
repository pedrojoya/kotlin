package es.iessaladillo.pedrojoya.pr169.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import es.iessaladillo.pedrojoya.pr169.base.RequestState
import es.iessaladillo.pedrojoya.pr169.data.remote.Api
import es.iessaladillo.pedrojoya.pr169.data.remote.YandexLiveData

internal class MainActivityViewModel(api: Api) : ViewModel() {

    private val yandexLiveData: YandexLiveData = YandexLiveData(api)

    val translation: LiveData<RequestState>
        get() = yandexLiveData

    fun translateFromApi(word: String) {
        yandexLiveData.translate(word)
    }

    override fun onCleared() {
        yandexLiveData.cancel()
        super.onCleared()
    }

}
