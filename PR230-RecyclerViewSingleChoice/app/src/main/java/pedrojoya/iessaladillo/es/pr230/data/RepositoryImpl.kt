package pedrojoya.iessaladillo.es.pr230.data

import pedrojoya.iessaladillo.es.pr230.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
