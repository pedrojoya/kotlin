package pedrojoya.iessaladillo.es.pr230.base

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

// Clase que usa el tracker para preguntar por los detalles de un determinado ítem
// del recyclerview. Esta clase a su vez pregunta al viewHolder, que implemente la interfaz
// DetailsProvider.
class PositionalDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {

    // Retorna el detalle del elemento del recyclerview sobre el que nos hemos movido,
    // que lo obtiene desde su viewHolder.
    override fun getItemDetails(e: MotionEvent): ItemDetailsLookup.ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            val viewHolder = recyclerView.getChildViewHolder(view)
            // El viewHolder debe implementar DetailsProvider para poder pedirle
            // los detalles.
            if (viewHolder is DetailsProvider) {
                // Le pedimos al viewHolder los detalles.
                return (viewHolder as DetailsProvider).getItemDetails()
            }
        }
        return null
    }

    interface DetailsProvider {

        // El ViewHolder debe tener un método que retorne los detalles del elemento
        // correspondiente del recyclerview.
        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long>

    }

}