package es.iessaladillo.pedrojoya.pr095.data

import es.iessaladillo.pedrojoya.pr095.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database