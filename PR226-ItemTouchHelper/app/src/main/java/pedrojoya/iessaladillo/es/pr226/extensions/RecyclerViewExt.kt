@file:JvmName("RecyclerViewExt")

package pedrojoya.iessaladillo.es.pr226.extensions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.onTouch(dragDirs: Int, swipeDirs: Int, init: OnItemTouchHelper.() -> Unit) {
    // Se crea el objeto auxiliar de configuración.
    val onItemTouchHelper = OnItemTouchHelper()
    // Se configura con el bloque de código recibido.
    onItemTouchHelper.init()

    // Se crea el objeto de verdad.
    val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
        // Drag & drop.
        override fun onMove(recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder): Boolean =
                onItemTouchHelper.onMoveListener?.invoke(recyclerView, viewHolder, target) ?: false

        // Swipe
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            onItemTouchHelper.onSwipeListener?.invoke(viewHolder, direction)
        }
    })
    // Se enlaza con el RecyclerView.
    itemTouchHelper.attachToRecyclerView(this)
}

class OnItemTouchHelper {

    var onMoveListener:
            ((recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) -> Boolean)? = null

    var onSwipeListener: ((viewHolder: RecyclerView.ViewHolder, direction: Int) -> Unit)? = null

    fun onMove(action: (recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder) -> Boolean) {
        onMoveListener = action
    }

    fun onSwiped(action: (viewHolder: RecyclerView.ViewHolder, direction: Int) -> Unit) {
        onSwipeListener = action
    }

}

