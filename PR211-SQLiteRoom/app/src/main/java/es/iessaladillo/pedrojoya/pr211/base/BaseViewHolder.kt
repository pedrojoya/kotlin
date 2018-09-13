package es.iessaladillo.pedrojoya.pr211.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View,
                              onItemClickListener: BaseListAdapter.OnItemClickListener? = null,
                              onItemLongClickListener: BaseListAdapter.OnItemLongClickListener? = null) :
        RecyclerView.ViewHolder(itemView) {

    init {
        onItemClickListener?.run {
            itemView.setOnClickListener { v ->
                onItemClick(v, adapterPosition)
            }
        }
        onItemLongClickListener?.run {
            itemView.setOnLongClickListener { v ->
                onItemLongClick(v, adapterPosition)
            }
        }
    }

}
