package es.iessaladillo.pedrojoya.pr021.data

import es.iessaladillo.pedrojoya.pr021.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
