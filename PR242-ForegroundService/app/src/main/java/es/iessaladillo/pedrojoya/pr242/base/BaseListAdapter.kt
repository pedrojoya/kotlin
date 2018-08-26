@file:Suppress("unused")

package es.iessaladillo.pedrojoya.pr242.base

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

// Kotlin only supports automatic SAM conversions for Java interfaces.
// So we create an extension function for conversion, maintaining compatibility
// with Java.
fun <M, V: BaseViewHolder<M>> BaseListAdapter<M, V>.setOnItemClickListener(
        action: (View, M, Int, Long) -> Unit) {
    onItemClickListener = object: OnItemClickListener<M> {
        override fun onItemClick(view: View, item: M, position: Int, id: Long) {
            action(view, item, position, id)
        }
    }
}
fun <M, V: BaseViewHolder<M>> BaseListAdapter<M, V>.setOnItemLongClickListener(
        action: (View, M, Int, Long) -> Boolean) {
    onItemLongClickListener = object: OnItemLongClickListener<M> {
        override fun onItemLongClick(view: View, item: M, position: Int, id: Long): Boolean {
            return action(view, item, position, id)
        }
    }
}


// V is ViewModel type, M is Model type.
@Suppress("UNUSED")
abstract class BaseListAdapter<M, V : BaseViewHolder<M>>(
        private var data: ArrayList<M> = ArrayList()) : RecyclerView.Adapter<V>() {

    var onItemClickListener: OnItemClickListener<M>? = null

    var onItemLongClickListener: OnItemLongClickListener<M>? = null

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

    fun submitList(data: ArrayList<M>) {
        this.data = data
        notifyDataSetChanged()
    }

    private fun checkEmptyViewVisibility() =
            emptyView?.let { it.visibility = if (itemCount == 0) View.VISIBLE else View.INVISIBLE }

    override fun getItemCount() = data.size

    fun getItem(position: Int) = data[position]

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(item: M) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun insertItem(item: M, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    fun swapItems(from: Int, to: Int) {
        Collections.swap(data, from, to)
        notifyItemMoved(from, to)
    }

    @CallSuper
    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(getItem(position))
    }
}

abstract class BaseViewHolder<M>
        protected constructor(itemView: View, adapter: BaseListAdapter<M, out BaseViewHolder<M>>) :
                RecyclerView.ViewHolder(itemView) {

    init {
        adapter.onItemClickListener?.let {
            itemView.setOnClickListener { v ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    it.onItemClick(v, adapter.getItem(adapterPosition), adapterPosition, itemId)
                }
            }
        }
        adapter.onItemLongClickListener?.let {
            itemView.setOnLongClickListener { v ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    return@setOnLongClickListener it.onItemLongClick(v,adapter.getItem(adapterPosition), adapterPosition, itemId)
                }
                false
            }
        }
    }

    abstract fun bind(item: M)

}

interface OnItemClickListener<M> {
    fun onItemClick(view: View, item: M, position: Int, id:Long)
}

interface OnItemLongClickListener<M> {
    fun onItemLongClick(view: View, item: M, position: Int, id:Long): Boolean
}


