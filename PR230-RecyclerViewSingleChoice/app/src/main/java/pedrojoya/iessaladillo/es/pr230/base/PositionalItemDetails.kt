package pedrojoya.iessaladillo.es.pr230.base

import androidx.recyclerview.selection.ItemDetailsLookup

// Clase que contiene los detalles de selección de un determinado item.
// Cada viewHolder crea un objeto de esta clase en su método getItemDetails().
// En este caso se usa la posición en el adaptador como clave.
class PositionalItemDetails(private val adapterPosition: Int) : ItemDetailsLookup.ItemDetails<Long>() {

    override fun getPosition(): Int = adapterPosition

    override fun getSelectionKey(): Long? = adapterPosition.toLong()

}