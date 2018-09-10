package es.iessaladillo.pedrojoya.pr140.data.remote

import androidx.lifecycle.MutableLiveData

import es.iessaladillo.pedrojoya.pr140.base.Event
import es.iessaladillo.pedrojoya.pr140.base.RequestState
import es.iessaladillo.pedrojoya.pr140.data.remote.model.Counting
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountingLiveData(private val api: Api) : MutableLiveData<RequestState>() {

    private var call: Call<Counting>? = null

    fun loadData(cityCode: String) {
        postValue(RequestState.Loading(true))
        call = api.getCityCounting(cityCode)
        call?.enqueue(object : Callback<Counting> {
            override fun onResponse(call: Call<Counting>, response: Response<Counting>) {
                if (response.body() != null && response.isSuccessful) {
                    postValue(RequestState.Result(response.body()!!))
                } else {
                    postValue(RequestState.Error(Event(Exception(response.message()))))
                }
            }

            override fun onFailure(call: Call<Counting>, t: Throwable) {
                postValue(RequestState.Error(Event(Exception(t.message))))
            }
        })
    }

    fun cancel() {
        call?.cancel()
    }

}
