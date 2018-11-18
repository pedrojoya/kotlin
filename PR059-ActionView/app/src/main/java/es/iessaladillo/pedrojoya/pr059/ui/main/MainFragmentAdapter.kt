package es.iessaladillo.pedrojoya.pr059.ui.main

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import es.iessaladillo.pedrojoya.pr059.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr059.base.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.support_simple_spinner_dropdown_item.*

class MainFragmentAdapter : BaseListAdapter<String, MainFragmentAdapter.ViewHolder>(object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return TextUtils.equals(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return TextUtils.equals(oldItem, newItem)
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View) : BaseViewHolder(containerView,
            onItemClickListener), LayoutContainer {

        fun bind(student: String) {
            text1.text = student
        }

    }

}
