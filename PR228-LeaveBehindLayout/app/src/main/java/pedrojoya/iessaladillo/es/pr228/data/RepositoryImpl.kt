package pedrojoya.iessaladillo.es.pr228.data

import pedrojoya.iessaladillo.es.pr228.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
