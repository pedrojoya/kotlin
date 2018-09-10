package pedrojoya.iessaladillo.es.pr201.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

// Kotlin only supports automatic SAM conversions for Java interfaces.
// So we create an extension function for conversion, maintaining compatibility
// with Java.
fun <M, VH: BaseViewHolder> BaseListAdapter<M, VH>.setOnItemClickListener(
        action: (View, Int) -> Unit) {
    onItemClickListener = object: BaseListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            action(view, position)
        }
    }
}
fun <M, VH: BaseViewHolder> BaseListAdapter<M, VH>.setOnItemLongClickListener(
        action: (View, Int) -> Boolean) {
    onItemLongClickListener = object: BaseListAdapter.OnItemLongClickListener {
        override fun onItemLongClick(view: View, position: Int): Boolean {
            return action(view, position)
        }
    }
}


// M is Model type.
@Suppress("UNUSED")
abstract class BaseListAdapter<M, VH: BaseViewHolder>(
        private var data: List<M> = ArrayList(),
        val diffUtilItemCallback: DiffUtil.ItemCallback<M>
) : RecyclerView.Adapter<VH>() {

    var onItemClickListener: OnItemClickListener? = null

    var onItemLongClickListener: OnItemLongClickListener? = null

    var emptyView: View? = null
        set(value) {
            field?.let { unregisterAdapterDataObserver(adapterDataObserver) }
            field = value
            registerAdapterDataObserver(adapterDataObserver)
            checkEmptyViewVisibility()
        }

    private val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkEmptyViewVisibility()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkEmptyViewVisibility()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkEmptyViewVisibility()
        }
    }

    fun submitList(newList: List<M>) {
        val diffUtilCallback = object: DiffUtil.Callback() {

            override fun getOldListSize(): Int = data.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    diffUtilItemCallback.areItemsTheSame(data[oldItemPosition], newList[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    diffUtilItemCallback.areContentsTheSame(data[oldItemPosition], newList[newItemPosition])

        }
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        data = newList
        diffResult.dispatchUpdatesTo(this)
    }

    private fun checkEmptyViewVisibility() =
            emptyView?.let { it.visibility = if (itemCount == 0) View.VISIBLE else View.INVISIBLE }

    override fun getItemCount() = data.size

    fun getItem(position: Int) = data[position]

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }

}
