package es.iessaladillo.pedrojoya.pr211.data

import es.iessaladillo.pedrojoya.pr211.data.local.StudentDao

class RepositoryImpl constructor(studentDao: StudentDao) : Repository by studentDao