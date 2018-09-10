package es.iessaladillo.pedrojoya.pr017.data

import es.iessaladillo.pedrojoya.pr017.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
