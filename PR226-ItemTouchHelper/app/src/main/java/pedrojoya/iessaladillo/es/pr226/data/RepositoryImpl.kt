package pedrojoya.iessaladillo.es.pr226.data

import pedrojoya.iessaladillo.es.pr226.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
