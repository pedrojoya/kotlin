package es.iessaladillo.pedrojoya.pr080.data

import es.iessaladillo.pedrojoya.pr080.base.Call
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource
import es.iessaladillo.pedrojoya.pr080.data.echo.EchoDataSource
import es.iessaladillo.pedrojoya.pr080.data.search.SearchDataSource

class RepositoryImpl(private val searchDataSource: SearchDataSource, private val echoDataSource: EchoDataSource) : Repository {

    override fun search(text: String): Call<Resource<Event<String>>> {
        return searchDataSource.search(text)
    }

    override fun requestEcho(text: String): Call<Resource<Event<String>>> {
        return echoDataSource.requestEcho(text)
    }

}
