package es.iessaladillo.pedrojoya.pr083.base

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.LayoutRes
import es.iessaladillo.pedrojoya.pr083.extensions.inflate
import kotlin.properties.Delegates

abstract class AdapterViewBaseAdapter<T, VH>(_data: List<T> = emptyList(),
                      @LayoutRes private val layoutResId: Int) : BaseAdapter() {

    var data: List<T> by Delegates.observable(_data) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: parent.inflate(layoutResId)
        @Suppress("UNCHECKED_CAST")
        val viewHolder: VH = convertView?.tag as? VH ?: onCreateViewHolder(itemView).also {
            itemView.tag = it }
        onBindViewHolder(viewHolder, position)
        return itemView
    }

    protected abstract fun onCreateViewHolder(itemView: View): VH

    override fun getCount() = data.size

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int) = position.toLong()

    protected abstract fun onBindViewHolder(holder: VH, position: Int)

}
