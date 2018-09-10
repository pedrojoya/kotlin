package pedrojoya.iessaladillo.es.pr201.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

// Kotlin only supports automatic SAM conversions for Java interfaces.
// So we create an extension function for conversion, maintaining compatibility
// with Java.
fun <M> BaseAdapter<M>.setOnItemClickListener(
        action: (View, Int) -> Unit) {
    onItemClickListener = object: OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            action(view, position)
        }
    }
}
fun <M> BaseAdapter<M>.setOnItemLongClickListener(
        action: (View, Int) -> Boolean) {
    onItemLongClickListener = object: OnItemLongClickListener {
        override fun onItemLongClick(view: View, position: Int): Boolean {
            return action(view, position)
        }
    }
}


// M is Model type.
@Suppress("UNUSED")
abstract class BaseAdapter<M>(
        private var data: List<M> = ArrayList(),
        @LayoutRes private val layoutResId: Int,
        val diffUtilItemCallback: DiffUtil.ItemCallback<M>
) : RecyclerView.Adapter<ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        val viewHolder = ViewHolder(itemView)
        onItemClickListener?.let {
            itemView.setOnClickListener { v ->
                if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                    it.onItemClick(v, viewHolder.adapterPosition)
                }
            }
        }
        onItemLongClickListener?.let {
            itemView.setOnLongClickListener { v ->
                if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                    return@setOnLongClickListener it.onItemLongClick(v, viewHolder.adapterPosition)
                }
                false
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.bind(getItem(position))
    }

    abstract fun View.bind(item: M)
}

class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer

interface OnItemClickListener {
    fun onItemClick(view: View, position: Int)
}

interface OnItemLongClickListener {
    fun onItemLongClick(view: View, position: Int): Boolean
}
