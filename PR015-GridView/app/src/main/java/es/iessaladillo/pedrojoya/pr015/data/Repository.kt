package es.iessaladillo.pedrojoya.pr015.data

import es.iessaladillo.pedrojoya.pr015.data.local.model.Word

interface Repository {

    fun queryWords(): List<Word>
    fun addWord(word: Word)
    fun deleteWord(position: Int)

}
