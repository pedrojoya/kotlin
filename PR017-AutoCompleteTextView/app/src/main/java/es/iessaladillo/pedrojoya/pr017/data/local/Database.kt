package es.iessaladillo.pedrojoya.pr017.data.local

import es.iessaladillo.pedrojoya.pr017.R
import es.iessaladillo.pedrojoya.pr017.data.Repository
import es.iessaladillo.pedrojoya.pr017.data.local.model.Word
import java.util.*

object Database: Repository {

    private val words: ArrayList<Word> = arrayListOf(
            Word(R.drawable.animal, "Animal", "Animal"),
            Word(R.drawable.bridge, "Bridge", "Puente"),
            Word(R.drawable.flag, "Flag", "Bandera"),
            Word(R.drawable.food, "Food", "Comida"),
            Word(R.drawable.fruit, "Fruit", "Fruta"),
            Word(R.drawable.glass, "Glass", "Vaso"),
            Word(R.drawable.plant, "Plant", "Planta"),
            Word(R.drawable.science, "Science", "Ciencia"),
            Word(R.drawable.sea, "Sea", "Mar")
    )

    override fun queryWords() = words

    override fun addWord(word: Word) {
        words.add(word)
    }

    override fun deleteWord(position: Int) {
        words.removeAt(position)
    }

}
