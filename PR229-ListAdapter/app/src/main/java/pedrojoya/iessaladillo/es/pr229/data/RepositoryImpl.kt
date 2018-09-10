package pedrojoya.iessaladillo.es.pr229.data

import pedrojoya.iessaladillo.es.pr229.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
