package es.iessaladillo.pedrojoya.pr083.data.remote

import android.content.Context

import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import es.iessaladillo.pedrojoya.pr083.base.SingletonHolder

class VolleyInstance private constructor(context: Context) {

    val requestQueue: RequestQueue by lazy { Volley.newRequestQueue(context.applicationContext) }

    companion object: SingletonHolder<VolleyInstance, Context>(::VolleyInstance)

}
