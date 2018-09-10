package es.iessaladillo.pedrojoya.pr169.data.remote

import es.iessaladillo.pedrojoya.pr169.data.models.TranslateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("translate")
    fun getTranslation(
            @Query(PARAM_API_KEY) key: String,
            @Query(PARAM_TEXT) text: String,
            @Query(PARAM_LANG) lang: String): Call<TranslateResponse>

}
