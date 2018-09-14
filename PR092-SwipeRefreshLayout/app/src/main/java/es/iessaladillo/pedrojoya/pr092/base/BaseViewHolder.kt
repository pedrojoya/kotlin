package es.iessaladillo.pedrojoya.pr092.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View,
                              onItemClickListener: BaseListAdapter.OnItemClickListener? = null,
                              onItemLongClickListener: BaseListAdapter.OnItemLongClickListener? = null) :
        RecyclerView.ViewHolder(itemView) {

    init {
        onItemClickListener?.let {
            itemView.setOnClickListener { v ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    it.onItemClick(v, adapterPosition)
                }
            }
        }
        onItemLongClickListener?.let {
            itemView.setOnLongClickListener { v ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    return@setOnLongClickListener it.onItemLongClick(v, adapterPosition)
                }
                false
            }
        }
    }

}
