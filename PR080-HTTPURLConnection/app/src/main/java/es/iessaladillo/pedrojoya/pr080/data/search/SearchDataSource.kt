package es.iessaladillo.pedrojoya.pr080.data.search

interface SearchDataSource {

    fun search(text: String): SearchRequest

}
