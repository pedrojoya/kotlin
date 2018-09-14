package es.iessaladillo.pedrojoya.pr059.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import es.iessaladillo.pedrojoya.pr059.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr059.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr059.extensions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.support_simple_spinner_dropdown_item.*

class MainActivityAdapter: BaseListAdapter<String, MainActivityAdapter.ViewHolder>(diffUtilItemCallback), Filterable {

    private var original: List<String>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(android.R.layout.simple_list_item_1))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<String>?) {
        super.submitList(list)
        original = list
    }

    fun submitFilteredList(list: List<String>) {
        super.submitList(list)
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filtered: List<String> = original?.filter { it.contains(constraint.toString(),
                    true) } ?: emptyList()
            return Filter.FilterResults().apply {
                values = filtered
                count = filtered.size
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.count ?: 0 > 0) {
                @Suppress("UNCHECKED_CAST")
                submitFilteredList(results?.values as List<String>? ?: emptyList())
            } else {
                submitFilteredList(emptyList())
            }
        }
    }

    inner class ViewHolder(override val containerView: View) : BaseViewHolder(containerView,
            onItemClickListener, onItemLongClickListener), LayoutContainer {

        fun bind(student: String) {
            text1.text = student
        }

    }

    companion object {

        private val diffUtilItemCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }

    }

}