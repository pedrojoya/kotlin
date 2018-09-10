package pedrojoya.iessaladillo.es.pr230.base

import android.view.View
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<M, VH: RecyclerView.ViewHolder>(diffUtilItemCallback: DiffUtil.ItemCallback<M>) :
        ListAdapter<M, VH>(diffUtilItemCallback) {

    var emptyView: View? = null
        set(value) {
            field = value
            checkEmptyViewVisibility(itemCount)
        }

    private var onItemClickListener: OnItemClickListener? = null

    fun getOnItemClickListener() = onItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setOnItemClickListener(action: (View, Int) -> Unit) {
        onItemClickListener = object: BaseListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                action(view, position)
            }
        }
    }

    // El adaptador debe recibir el selectionTracker para que el ViewHolder pueda
    // saber el estado de selección del elemento.
    var selectionTracker: SelectionTracker<Long>? = null


    override fun submitList(list: List<M>?) {
        checkEmptyViewVisibility(list?.size ?: 0)
        super.submitList(list)
    }

    private fun checkEmptyViewVisibility(size: Int) {
        if (emptyView != null) {
            emptyView!!.visibility = if (size == 0) View.VISIBLE else View.INVISIBLE
        }
    }

    public override fun getItem(position: Int): M {
        return super.getItem(position)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

}