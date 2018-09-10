package es.iessaladillo.pedrojoya.pr140.data.remote

import es.iessaladillo.pedrojoya.pr140.data.remote.model.Counting
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("{cityCode}.xml")
    fun getCityCounting(@Path("cityCode") cityCode: String): Call<Counting>

}
