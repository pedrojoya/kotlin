package es.iessaladillo.pedrojoya.pr016.base

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import es.iessaladillo.pedrojoya.pr016.extensions.inflate

abstract class StardardExpandableListAdapter<G, C, GVH, CVH>(
        @Suppress("UNUSED")
        private val groups: List<G>,
        private val children: List<List<C>>,
        @LayoutRes private val groupLayoutResId: Int,
        @LayoutRes private val childLayoutResId: Int) : BaseExpandableListAdapter() {


    override fun getChild(groupPosition: Int, childPosition: Int) = children[groupPosition][childPosition]

    override fun getChildId(groupPosition: Int, childPosition: Int) = 0L

    @Suppress("UNCHECKED_CAST")
    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean,
                              convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: parent.inflate(childLayoutResId)
        val viewHolder: CVH = convertView?.tag as? CVH ?: onCreateChildViewHolder(itemView)
        itemView.tag = viewHolder
        onBindChildViewHolder(viewHolder, groupPosition, childPosition)
        return itemView
    }

    abstract fun onCreateChildViewHolder(itemView: View): CVH

    abstract fun onBindChildViewHolder(viewHolder: CVH, groupPosition: Int, childPosition: Int)

    override fun getChildrenCount(groupPosition: Int) = children[groupPosition].size

    override fun getGroup(groupPosition: Int) = children[groupPosition]

    override fun getGroupCount() = children.size

    override fun getGroupId(groupPosition: Int) = 0L

    @Suppress("UNCHECKED_CAST")
    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?,
                              parent: ViewGroup): View {
        val itemView = convertView ?: parent.inflate(groupLayoutResId)
        val viewHolder: GVH = convertView?.tag as? GVH ?: onCreateGroupViewHolder(itemView)
        itemView.tag = viewHolder
        onBindGroupViewHolder(viewHolder, groupPosition, isExpanded)
        return itemView
    }

    abstract fun onBindGroupViewHolder(viewHolder: GVH, groupPosition: Int, expanded: Boolean)

    abstract fun onCreateGroupViewHolder(itemView: View): GVH

    override fun hasStableIds() = false

    override fun isChildSelectable(posGrupo: Int, posHijo: Int) = true

}