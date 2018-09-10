package es.iessaladillo.pedrojoya.pr021.data.local

import es.iessaladillo.pedrojoya.pr021.R
import es.iessaladillo.pedrojoya.pr021.data.Repository
import es.iessaladillo.pedrojoya.pr021.data.local.model.Country

object Database : Repository {

    private val countries = arrayListOf(
            Country(R.drawable.de, "Germay"),
            Country(R.drawable.dk, "Denmark"),
            Country(R.drawable.es, "Spain"),
            Country(R.drawable.fi, "Finland"),
            Country(R.drawable.fr, "France"),
            Country(R.drawable.gr, "Greece"),
            Country(R.drawable.nl, "Holand"),
            Country(R.drawable.ie, "Ireland"),
            Country(R.drawable.`is`, "Iceland"),
            Country(R.drawable.it, "Italy"),
            Country(R.drawable.lt, "Lithuania"),
            Country(R.drawable.no, "Norway"),
            Country(R.drawable.pl, "Poland"),
            Country(R.drawable.pt, "Portugal"))

    override fun queryCountries() = countries

}
