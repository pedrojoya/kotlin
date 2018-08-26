package es.iessaladillo.pedrojoya.pr095.data.local

import es.iessaladillo.pedrojoya.pr095.data.model.Song

interface Repository {

    fun querySongs(): List<Song>

}
