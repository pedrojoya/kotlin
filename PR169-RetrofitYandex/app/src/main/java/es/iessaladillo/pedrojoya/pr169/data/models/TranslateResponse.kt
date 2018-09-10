package es.iessaladillo.pedrojoya.pr169.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class TranslateResponse {

    @SerializedName("code")
    @Expose
    var code: Int = 0
    @Suppress("unused")
    @SerializedName("lang")
    @Expose
    var lang: String = ""
    @SerializedName("text")
    @Expose
    var text: List<String> = ArrayList()

}
