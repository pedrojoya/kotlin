package pedrojoya.iessaladillo.es.pr106.data

import pedrojoya.iessaladillo.es.pr106.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
