package es.iessaladillo.pedrojoya.pr021.main

import android.view.View
import es.iessaladillo.pedrojoya.pr021.R
import es.iessaladillo.pedrojoya.pr021.base.DropDownBaseAdapter
import es.iessaladillo.pedrojoya.pr021.data.local.model.Country
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item_collapsed.*
import kotlinx.android.synthetic.main.activity_main_item_expanded.*

class MainActivityAdapter(data: List<Country>) :
        DropDownBaseAdapter<Country, MainActivityAdapter.CollapsedViewHolder, MainActivityAdapter.ExpandedViewHolder>(data,
                R.layout.activity_main_item_collapsed, R.layout.activity_main_item_expanded) {

    override fun onCreateCollapsedViewHolder(itemView: View): CollapsedViewHolder
            = CollapsedViewHolder(itemView)

    override fun onBindCollapsedViewHolder(holder: CollapsedViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateExpandedViewHolder(itemView: View): ExpandedViewHolder
            = ExpandedViewHolder(itemView)

    override fun onBindExpandedViewHolder(holder: ExpandedViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class CollapsedViewHolder(override val containerView: View) : LayoutContainer {

        fun bind(country: Country) {
            imgFlagCollapsed.setImageResource(country.flagResId)
            lblNameCollapsed.text = country.name
        }

    }

    class ExpandedViewHolder(override val containerView: View) : LayoutContainer {

        fun bind(country: Country) {
            imgFlagExpanded.setImageResource(country.flagResId)
            lblNameExpanded.text = country.name
        }

    }

}
