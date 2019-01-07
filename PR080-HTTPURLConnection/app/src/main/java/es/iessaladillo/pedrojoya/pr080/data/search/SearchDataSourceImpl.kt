package es.iessaladillo.pedrojoya.pr080.data.search

import es.iessaladillo.pedrojoya.pr080.base.Call
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource

class SearchDataSourceImpl : SearchDataSource {

    override fun search(text: String): Call<Resource<Event<String>>> {
        return SearchRequest(text)
    }

}
