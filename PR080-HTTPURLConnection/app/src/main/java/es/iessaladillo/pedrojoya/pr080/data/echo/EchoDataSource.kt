package es.iessaladillo.pedrojoya.pr080.data.echo

import es.iessaladillo.pedrojoya.pr080.base.Call
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource

interface EchoDataSource {

    fun requestEcho(text: String): Call<Resource<Event<String>>>

}
