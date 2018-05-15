package es.iessaladillo.pedrojoya.pr015.data

interface Repository {

    fun queryWords(): List<Word>
    fun addWord(word: Word)
    fun deleteWord(position: Int)

}

class RepositoryImpl(private val database: Database) : Repository by database


