package es.iessaladillo.pedrojoya.pr080.data

import es.iessaladillo.pedrojoya.pr080.data.echo.EchoDataSource
import es.iessaladillo.pedrojoya.pr080.data.echo.EchoRequest
import es.iessaladillo.pedrojoya.pr080.data.search.SearchDataSource
import es.iessaladillo.pedrojoya.pr080.data.search.SearchRequest

class RepositoryImpl(private val searchDataSource: SearchDataSource, private val echoDataSource: EchoDataSource) : Repository {

    override fun search(text: String): SearchRequest {
        return searchDataSource.search(text)
    }

    override fun requestEcho(text: String): EchoRequest {
        return echoDataSource.requestEcho(text)
    }

}
