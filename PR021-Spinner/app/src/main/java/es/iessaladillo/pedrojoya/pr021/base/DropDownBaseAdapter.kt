package es.iessaladillo.pedrojoya.pr021.base

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import es.iessaladillo.pedrojoya.pr021.extensions.inflate

abstract class DropDownBaseAdapter<out T, CVH, EVH>
protected constructor(protected val data: List<T>,
                      @LayoutRes private val collapsedLayoutResId: Int,
                      @LayoutRes private val expandedLayoutResId: Int) : BaseAdapter() {

    @Suppress("UNCHECKED_CAST")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: parent.inflate(collapsedLayoutResId)
        val viewHolder: CVH = convertView?.tag as? CVH ?: onCreateCollapsedViewHolder(itemView)
        itemView.tag = viewHolder
        onBindCollapsedViewHolder(viewHolder, position)
        return itemView
    }

    override fun getCount() = data.size

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int) = position.toLong()

    protected abstract fun onCreateCollapsedViewHolder(itemView: View): CVH

    protected abstract fun onBindCollapsedViewHolder(holder: CVH, position: Int)

    @Suppress("UNCHECKED_CAST")
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: parent.inflate(expandedLayoutResId)
        val viewHolder: EVH = convertView?.tag as? EVH ?: onCreateExpandedViewHolder(itemView)
        itemView.tag = viewHolder
        onBindExpandedViewHolder(viewHolder, position)
        return itemView
    }

    protected abstract fun onCreateExpandedViewHolder(itemView: View): EVH

    protected abstract fun onBindExpandedViewHolder(holder: EVH, position: Int)

}

