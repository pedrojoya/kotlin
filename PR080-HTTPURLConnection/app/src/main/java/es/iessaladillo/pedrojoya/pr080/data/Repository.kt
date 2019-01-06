package es.iessaladillo.pedrojoya.pr080.data

import es.iessaladillo.pedrojoya.pr080.data.echo.EchoRequest
import es.iessaladillo.pedrojoya.pr080.data.search.SearchRequest

interface Repository {

    fun search(text: String): SearchRequest
    fun requestEcho(text: String): EchoRequest

}
