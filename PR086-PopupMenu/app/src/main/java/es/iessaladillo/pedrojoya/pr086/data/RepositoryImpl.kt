package es.iessaladillo.pedrojoya.pr086.data

import es.iessaladillo.pedrojoya.pr086.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database