package es.iessaladillo.pedrojoya.pr080.data

import es.iessaladillo.pedrojoya.pr080.base.Call
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource

interface Repository {

    fun search(text: String): Call<Resource<Event<String>>>
    fun requestEcho(text: String): Call<Resource<Event<String>>>

}
