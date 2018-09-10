package es.iessaladillo.pedrojoya.pr194.data.remote

import androidx.lifecycle.MutableLiveData

import es.iessaladillo.pedrojoya.pr194.base.Event
import es.iessaladillo.pedrojoya.pr194.base.RequestState
import es.iessaladillo.pedrojoya.pr194.data.model.Student
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsLiveData(private val api: Api) : MutableLiveData<RequestState>() {

    private var call: Call<List<Student>>? = null

    init {
        loadData()
    }

    fun loadData() {
        postValue(RequestState.Loading(true))
        call = api.students
        call?.enqueue(object : Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                if (response.body() != null && response.isSuccessful) {
                    postValue(RequestState.Result(response.body()!!))
                } else {
                    postValue(RequestState.Error(Event(Exception(response.message()))))
                }
            }

            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                postValue(RequestState.Error(Event(Exception(t.message))))
            }
        })
    }

    fun cancel() {
        call?.cancel()
    }

}
