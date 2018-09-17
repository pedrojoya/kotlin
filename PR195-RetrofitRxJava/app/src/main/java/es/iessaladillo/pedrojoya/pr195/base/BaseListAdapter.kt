@file:Suppress("unused")

package es.iessaladillo.pedrojoya.pr195.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

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
                              diffUtilItemCallback: DiffUtil.ItemCallback<M>) :
        ListAdapter<M, VH>(diffUtilItemCallback) {

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
