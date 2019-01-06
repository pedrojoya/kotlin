package es.iessaladillo.pedrojoya.pr080.data.search

class SearchDataSourceImpl : SearchDataSource {

    override fun search(text: String): SearchRequest {
        return SearchRequest(text)
    }

}
