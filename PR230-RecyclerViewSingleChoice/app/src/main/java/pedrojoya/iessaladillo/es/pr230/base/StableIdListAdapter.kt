package pedrojoya.iessaladillo.es.pr230.base

import android.view.MotionEvent
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class StableIdListAdapter<M, VH : RecyclerView.ViewHolder>(
        diffUtilItemCallback: DiffUtil.ItemCallback<M>) : ListAdapter<M, VH>(diffUtilItemCallback) {

    var selectionTracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    final override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    final override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    public override fun getItem(position: Int): M {
        return super.getItem(position)
    }

    fun getItem(key: Long): M = getItem(key.toInt())

    fun getKey(position: Int) = position.toLong()

    class StableIdDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? =
                recyclerView.findChildViewUnder(e.x, e.y)?.let { view ->
                    recyclerView.getChildViewHolder(view)?.let { holder ->
                        StableIdItemDetails(holder)
                    }
                }
    }

    class StableIdItemDetails(private val holder: RecyclerView.ViewHolder) :
            ItemDetailsLookup.ItemDetails<Long>() {
        override fun getSelectionKey(): Long? = holder.adapterPosition.toLong()
        override fun getPosition(): Int = holder.adapterPosition
    }

    companion object {

        fun newSelectionTrackerBuilder(selectionId: String, recyclerView: RecyclerView): SelectionTracker.Builder<Long> =
                SelectionTracker.Builder<Long>(
                        selectionId,
                        recyclerView,
                        StableIdKeyProvider(recyclerView),
                        StableIdDetailsLookup(recyclerView),
                        StorageStrategy.createLongStorage())

        fun newSingleSelectionTrackerBuilder(selectionId: String, recyclerView: RecyclerView):
                SelectionTracker.Builder<Long> =
                SelectionTracker.Builder<Long>(
                        selectionId,
                        recyclerView,
                        StableIdKeyProvider(recyclerView),
                        StableIdDetailsLookup(recyclerView),
                        StorageStrategy.createLongStorage())
                        .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())

    }
}

