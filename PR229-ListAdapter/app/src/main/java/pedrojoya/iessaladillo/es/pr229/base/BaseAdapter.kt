package pedrojoya.iessaladillo.es.pr229.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
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

// V is ViewModel type, M is Model type.
abstract class BaseAdapter<M>(@LayoutRes private val layoutResId: Int,
                              diffUtilItemCallback: DiffUtil.ItemCallback<M>) :
        ListAdapter<M, ViewHolder>(diffUtilItemCallback) {

    var onItemClickListener: OnItemClickListener? = null
    var onItemLongClickListener: OnItemLongClickListener? = null
    var emptyView: View? = null
        set(value) {
            field = value
            checkEmptyViewVisibility(itemCount)
        }

    init {
        checkEmptyViewVisibility(itemCount)
    }

    private fun checkEmptyViewVisibility(size: Int) {
        emptyView?.run { visibility = if (size == 0) View.VISIBLE else View.INVISIBLE }
    }

    override fun submitList(list: List<M>?) {
        checkEmptyViewVisibility(list?.size ?: 0)
        super.submitList(list)
    }

    public override fun getItem(position: Int): M {
        return super.getItem(position)
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
