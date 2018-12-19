package es.iessaladillo.pedrojoya.pr057.base

import android.view.View

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

// V is ViewModel type, M is Model type.
abstract class BaseListAdapter<M, V : BaseViewHolder>(diffUtilItemCallback: DiffUtil.ItemCallback<M>) : ListAdapter<M, V>(diffUtilItemCallback) {

    var onItemClickListener: ((view: View, position: Int) -> Unit)? = null

    public override fun getItem(position: Int): M = super.getItem(position)

}
