@file:JvmName("RecyclerViewExt")

package es.iessaladillo.pedrojoya.pr212.extensions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setOnSwipeRightListener(action: (viewHolder: RecyclerView.ViewHolder) -> Unit) {
    val itemTouchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.RIGHT) {
        // Drag & drop.
        override fun onMove(recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder): Boolean = false

        // Swipe
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (direction == ItemTouchHelper.RIGHT) {
                action(viewHolder)
            }
        }
    })
    itemTouchHelper.attachToRecyclerView(this)
}
