package es.iessaladillo.pedrojoya.pr242.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.iessaladillo.pedrojoya.pr242.R
import es.iessaladillo.pedrojoya.pr242.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr242.base.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

class MainActivityAdapter(data: ArrayList<String>) : BaseListAdapter<String, MainActivityAdapter.ViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main_item,
                    parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(override val containerView: View) : BaseViewHolder(containerView,
            onItemClickListener,
            onItemLongClickListener), LayoutContainer {

        fun bind(item: String) {
            lblName.text = item
        }

    }

}
