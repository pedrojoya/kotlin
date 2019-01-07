package es.iessaladillo.pedrojoya.pr080.data.search

import es.iessaladillo.pedrojoya.pr080.base.Call
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource

interface SearchDataSource {

    fun search(text: String): Call<Resource<Event<String>>>

}
