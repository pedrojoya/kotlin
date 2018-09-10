package es.iessaladillo.pedrojoya.pr212.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class RecyclerListAdapter<M, V : RecyclerListAdapter<M, V>.ViewHolder>(
        diffUtilItemCallback: DiffUtil.ItemCallback<M>) : ListAdapter<M, V>(diffUtilItemCallback) {

    private var onItemClickListener: OnItemClickListener<M>? = null
    private var onItemLongClickListener: OnItemLongClickListener<M>? = null
    private var emptyView: View? = null
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

    interface OnItemClickListener<M> {
        fun onItemClick(adapter: RecyclerView.Adapter<*>, view: View, item: M,
                        position: Int, id: Long)
    }

    interface OnItemLongClickListener<M> {
        fun onItemLongClick(adapter: RecyclerView.Adapter<*>, view: View, item: M,
                            position: Int, id: Long): Boolean
    }

    fun setOnItemClickListener(listener: OnItemClickListener<M>) {
        this.onItemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener<M>) {
        this.onItemLongClickListener = listener
    }

    fun setEmptyView(emptyView: View) {
        if (this.emptyView != null) {
            unregisterAdapterDataObserver(adapterDataObserver)
        }
        this.emptyView = emptyView
        registerAdapterDataObserver(adapterDataObserver)
        checkEmptyViewVisibility()
    }

    private fun checkEmptyViewVisibility() {
        if (emptyView != null) {
            emptyView!!.visibility = if (itemCount == 0) View.VISIBLE else View.INVISIBLE
        }
    }

    abstract inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            onItemClickListener?.run {
                itemView.setOnClickListener { v ->
                    onItemClick(this@RecyclerListAdapter, v, getItem(adapterPosition), adapterPosition, itemId)
                }
            }
            onItemLongClickListener?.run {
                itemView.setOnLongClickListener { v ->
                    onItemLongClick(this@RecyclerListAdapter, v, getItem(adapterPosition), adapterPosition, itemId)
                }
            }
        }

    }

}