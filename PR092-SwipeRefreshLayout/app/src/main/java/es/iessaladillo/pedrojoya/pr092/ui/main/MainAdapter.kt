package es.iessaladillo.pedrojoya.pr092.ui.main

import android.view.View
import android.view.ViewGroup
import es.iessaladillo.pedrojoya.pr092.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr092.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr092.extensions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.support_simple_spinner_dropdown_item.*

class MainAdapter(data: ArrayList<String>) : BaseListAdapter<String, ViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(android.R.layout.simple_list_item_1), this)

}

class ViewHolder(override val containerView: View, adapter: MainAdapter) :
        BaseViewHolder<String>(containerView, adapter), LayoutContainer {

    override fun bind(item: String) {
        text1.text = item
    }

}

