package es.iessaladillo.pedrojoya.pr249.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View, onItemClickListener: BaseListAdapter.OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { v ->
            onItemClickListener?.onItemClick(v, adapterPosition)
        }
    }

}
