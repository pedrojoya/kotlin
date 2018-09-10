package pedrojoya.iessaladillo.es.pr231.data

import pedrojoya.iessaladillo.es.pr231.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
