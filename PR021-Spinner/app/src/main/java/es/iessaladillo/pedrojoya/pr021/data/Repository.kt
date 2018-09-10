package es.iessaladillo.pedrojoya.pr021.data

import es.iessaladillo.pedrojoya.pr021.data.local.model.Country

interface Repository {

    fun queryCountries(): List<Country>

}
