package es.iessaladillo.pedrojoya.pr057.base

import android.view.View

import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View, onItemClickListener: ((view: View, position:Int) -> Unit)? = null) :
        RecyclerView.ViewHolder(itemView) {

    init {
        onItemClickListener?.let {
            itemView.setOnClickListener { view ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    it.invoke(view, adapterPosition)
                }
            }
        }
    }

}
