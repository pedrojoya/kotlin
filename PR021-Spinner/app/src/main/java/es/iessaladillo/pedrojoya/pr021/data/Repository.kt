package es.iessaladillo.pedrojoya.pr021.data

interface Repository {

    fun queryCountries(): List<Country>

}

class RepositoryImpl(private val database: Database) : Repository by database
