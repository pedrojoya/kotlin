package es.iessaladillo.pedrojoya.pr132.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import es.iessaladillo.pedrojoya.pr132.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr132.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr132.extensions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.support_simple_spinner_dropdown_item.*

class MainFragmentAdapter: BaseListAdapter<String, MainFragmentAdapter.ViewHolder>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(android.R.layout.simple_list_item_1))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View): BaseViewHolder(containerView,
            onItemClickListener, onItemLongClickListener), LayoutContainer {

        fun bind(item: String) {
            text1.text = item
        }

    }

    companion object {

        private val diffUtilItemCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                    oldItem == newItem
        }

    }

}