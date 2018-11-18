package es.iessaladillo.pedrojoya.pr086.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View,
                              onItemClickListener: BaseListAdapter.OnItemClickListener? = null) :
        RecyclerView.ViewHolder(itemView) {

    init {
        onItemClickListener?.let {
            itemView.setOnClickListener { view ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    it.onItemClick(view, adapterPosition)
                }
            }
        }
    }

}

