package es.iessaladillo.pedrojoya.pr015.data

import es.iessaladillo.pedrojoya.pr015.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
