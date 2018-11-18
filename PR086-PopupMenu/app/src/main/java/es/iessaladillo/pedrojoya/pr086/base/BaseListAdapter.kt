@file:Suppress("unused")

package es.iessaladillo.pedrojoya.pr086.base

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

// M is Model type.
@Suppress("UNUSED")
abstract class BaseListAdapter<M, VH: BaseViewHolder>(
                              diffUtilItemCallback: DiffUtil.ItemCallback<M>) :
        ListAdapter<M, VH>(diffUtilItemCallback) {

    var onItemClickListener: OnItemClickListener? = null

    public override fun getItem(position: Int): M {
        return super.getItem(position)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

}
