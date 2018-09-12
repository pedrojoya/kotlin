package es.iessaladillo.pedrojoya.pr212.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

fun <M, V : BaseViewHolder> BaseListAdapter<M, V>.setOnItemClickListener(
        action: (view: View, position: Int) -> Unit) {
    onItemClickListener = object : BaseListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            action(view, position)
        }

    }
}

@Suppress("unused")
fun <M, V : BaseViewHolder> BaseListAdapter<M, V>.setOnItemLongClickListener(
        action: (view: View, position: Int) -> Boolean) {
    onItemLongClickListener = object : BaseListAdapter.OnItemLongClickListener {
        override fun onItemLongClick(view: View, position: Int): Boolean =
                action(view, position)
    }
}

abstract class BaseListAdapter<M, V : BaseViewHolder>(
        diffUtilItemCallback: DiffUtil.ItemCallback<M>) : ListAdapter<M, V>(diffUtilItemCallback) {

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

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }

}