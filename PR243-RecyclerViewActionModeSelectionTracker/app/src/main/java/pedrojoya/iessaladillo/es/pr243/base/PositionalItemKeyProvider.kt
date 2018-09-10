package pedrojoya.iessaladillo.es.pr243.base

import androidx.recyclerview.selection.ItemKeyProvider

// Clase que indica la clave de cada posición del recyclerview y viceversa.
// Usaremos la propia posición como key.
class PositionalItemKeyProvider : ItemKeyProvider<Long>(ItemKeyProvider.SCOPE_MAPPED) {

    // Retorna la clave que representa al elemento situado en una determianda posición.
    override fun getKey(position: Int): Long? = position.toLong()

    // Retorna la posición correspondiente a una determinada clave.
    override fun getPosition(key: Long): Int = key.toInt()

}
