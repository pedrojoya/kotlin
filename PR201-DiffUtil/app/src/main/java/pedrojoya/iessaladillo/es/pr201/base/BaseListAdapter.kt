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
abstract class BaseListAdapter<M, VH: BaseViewHolder> : RecyclerView.Adapter<VH>() {

    private var data: List<M> = ArrayList()

    var onItemClickListener: OnItemClickListener? = null

    var onItemLongClickListener: OnItemLongClickListener? = null

    fun submitList(newData: List<M>) {
        val diffResult = DiffUtil.calculateDiff(
                BaseDiffUtilCallback(data, newData), true)
        data = newData
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = data.size

    fun getItem(position: Int) = data[position]

    abstract fun areItemsTheSame(oldItem: M, newItem: M): Boolean

    abstract fun areContentsTheSame(oldItem: M, newItem: M): Boolean

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }

    private inner class BaseDiffUtilCallback(private val oldData: List<M>, private val newData: List<M>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return this@BaseListAdapter.areItemsTheSame(oldData[oldItemPosition],
                    newData[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return this@BaseListAdapter.areContentsTheSame(oldData[oldItemPosition],
                    newData[newItemPosition])
        }

    }

}
