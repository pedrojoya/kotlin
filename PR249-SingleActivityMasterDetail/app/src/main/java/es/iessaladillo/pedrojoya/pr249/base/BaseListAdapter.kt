package es.iessaladillo.pedrojoya.pr249.base

import android.view.View

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

// SAM only works with interfaces defined in Java, so we provide extension functions so we can
// use Kotlin lambdas directly.
fun <M, VH : BaseViewHolder> BaseListAdapter<M, VH>.setOnItemClickListener(
        action: (v: View, position:Int) -> Unit) {
    onItemClickListener = object : BaseListAdapter.OnItemClickListener {
        override fun onItemClick(v: View, position: Int) {
            action(v, position)
        }
    }
}

// M for Model, VH for ViewHolder
abstract class BaseListAdapter<M, VH : BaseViewHolder>(diffUtilItemCallback: DiffUtil.ItemCallback<M>) :
        ListAdapter<M, VH>(diffUtilItemCallback) {

    var onItemClickListener: OnItemClickListener? = null

    public override fun getItem(position: Int): M {
        return super.getItem(position)
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

}
