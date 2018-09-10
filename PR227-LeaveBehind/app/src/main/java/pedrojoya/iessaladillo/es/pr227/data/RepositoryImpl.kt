package pedrojoya.iessaladillo.es.pr227.data

import pedrojoya.iessaladillo.es.pr227.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
