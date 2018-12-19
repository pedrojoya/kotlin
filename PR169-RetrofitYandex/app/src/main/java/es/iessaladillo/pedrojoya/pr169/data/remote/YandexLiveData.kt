package es.iessaladillo.pedrojoya.pr169.data.remote

import androidx.lifecycle.MutableLiveData

import es.iessaladillo.pedrojoya.pr169.BuildConfig
import es.iessaladillo.pedrojoya.pr169.base.Event
import es.iessaladillo.pedrojoya.pr169.base.RequestState
import es.iessaladillo.pedrojoya.pr169.data.models.TranslateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YandexLiveData(private val api: Api) : MutableLiveData<RequestState<TranslateResponse>>() {

    private var call: Call<TranslateResponse>? = null

    fun translate(word: String) {
        postValue(RequestState.Loading)
        call = api.getTranslation(BuildConfig.YANDEX_API_KEY, word, LANG)
        call?.enqueue(object : Callback<TranslateResponse> {
            override fun onResponse(call: Call<TranslateResponse>,
                                    response: Response<TranslateResponse>) {
                if (response.body() != null && response.isSuccessful) {
                    postValue(RequestState.Result(response.body()!!))
                } else {
                    postValue(RequestState.Error(Event(Exception(response.message()))))
                }
            }

            override fun onFailure(call: Call<TranslateResponse>, t: Throwable) {
                postValue(RequestState.Error(Event(Exception(t.message))))
            }
        })
    }

    fun cancel() {
        call?.cancel()
    }

}
