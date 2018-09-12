package es.iessaladillo.pedrojoya.pr178.data

import es.iessaladillo.pedrojoya.pr178.data.local.Database

class RepositoryImpl(private val database: Database) : Repository by database
