package pedrojoya.iessaladillo.es.pr243.data

import pedrojoya.iessaladillo.es.pr243.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
