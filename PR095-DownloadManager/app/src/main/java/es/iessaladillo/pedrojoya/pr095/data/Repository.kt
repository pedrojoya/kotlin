package es.iessaladillo.pedrojoya.pr095.data

import es.iessaladillo.pedrojoya.pr095.data.model.Song

interface Repository {

    fun querySongs(): List<Song>

}
