package pedrojoya.iessaladillo.es.pr106.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View,
                     onItemClickListener: BaseListAdapter.OnItemClickListener? = null,
                     onItemLongClickListener: BaseListAdapter.OnItemLongClickListener? = null) :
        RecyclerView.ViewHolder(itemView) {

    init {
        onItemClickListener?.let {
            itemView.setOnClickListener { view ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    it.onItemClick(view, adapterPosition)
                }
            }
        }
        onItemLongClickListener?.let {
            itemView.setOnLongClickListener { view ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    return@setOnLongClickListener it.onItemLongClick(view, adapterPosition)
                }
                false
            }
        }
    }

}

