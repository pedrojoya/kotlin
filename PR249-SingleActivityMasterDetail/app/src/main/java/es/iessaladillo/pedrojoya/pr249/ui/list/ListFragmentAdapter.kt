package es.iessaladillo.pedrojoya.pr249.ui.list

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import es.iessaladillo.pedrojoya.pr249.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr249.base.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.support_simple_spinner_dropdown_item.*

class ListFragmentAdapter : BaseListAdapter<String, ListFragmentAdapter.ViewHolder>(object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return TextUtils.equals(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return TextUtils.equals(oldItem, newItem)
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListFragmentAdapter.ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false))

    override fun onBindViewHolder(viewHolder: ListFragmentAdapter.ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View) : BaseViewHolder(containerView,
            onItemClickListener), LayoutContainer {

        fun bind(item: String) {
            text1.text = item
        }

    }

}
