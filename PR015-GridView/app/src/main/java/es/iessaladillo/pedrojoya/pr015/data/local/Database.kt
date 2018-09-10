package es.iessaladillo.pedrojoya.pr015.data.local

import es.iessaladillo.pedrojoya.pr015.R
import es.iessaladillo.pedrojoya.pr015.data.Repository
import es.iessaladillo.pedrojoya.pr015.data.local.model.Word
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
            Word(R.drawable.sea, "Sea", "Mar"),
            Word(R.drawable.space, "Space", "Espacio"),
            Word(R.drawable.art, "Art", "Arte"),
            Word(R.drawable.furniture, "Furniture", "Mobiliario")
    )

    override fun queryWords() = words

    override fun addWord(word: Word) {
        words.add(word)
    }

    override fun deleteWord(position: Int) {
        words.removeAt(position)
    }

}
