package pedrojoya.iessaladillo.es.pr243.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import pedrojoya.iessaladillo.es.pr243.base.BaseListAdapter

abstract class BaseViewHolder(override val containerView: View,
                              onItemClickListener: BaseListAdapter.OnItemClickListener? = null) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        onItemClickListener?.let {
            itemView.setOnClickListener { v ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    it.onItemClick(v, adapterPosition)
                }
            }
        }
    }

}
