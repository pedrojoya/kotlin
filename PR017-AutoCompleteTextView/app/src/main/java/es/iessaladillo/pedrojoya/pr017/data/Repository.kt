package es.iessaladillo.pedrojoya.pr017.data

import es.iessaladillo.pedrojoya.pr017.data.local.model.Word

interface Repository {

    fun queryWords(): List<Word>
    fun addWord(word: Word)
    fun deleteWord(position: Int)

}
