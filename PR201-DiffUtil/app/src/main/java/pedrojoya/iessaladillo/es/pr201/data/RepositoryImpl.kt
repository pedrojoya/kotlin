package pedrojoya.iessaladillo.es.pr201.data

import pedrojoya.iessaladillo.es.pr201.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
