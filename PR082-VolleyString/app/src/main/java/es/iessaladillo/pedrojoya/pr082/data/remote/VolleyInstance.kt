package es.iessaladillo.pedrojoya.pr082.data.remote

import android.content.Context

import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyInstance private constructor(context: Context) {

    val requestQueue: RequestQueue by lazy { Volley.newRequestQueue(context.applicationContext) }

    companion object {

        @Volatile
        private var INSTANCE: VolleyInstance? = null

        fun getInstance(context: Context): VolleyInstance =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleyInstance(context.applicationContext).also {
                    INSTANCE = it
                }
            }

    }

}
