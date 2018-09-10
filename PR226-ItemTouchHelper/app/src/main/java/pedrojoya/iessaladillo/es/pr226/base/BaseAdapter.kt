@file:Suppress("unused")

package pedrojoya.iessaladillo.es.pr226.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import java.util.*
import kotlin.collections.ArrayList

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
        private var data: ArrayList<M> = ArrayList(),
        @LayoutRes private val layoutResId: Int
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
