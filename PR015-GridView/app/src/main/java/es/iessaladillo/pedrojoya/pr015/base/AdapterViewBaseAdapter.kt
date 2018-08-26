package es.iessaladillo.pedrojoya.pr015.base

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.LayoutRes
import es.iessaladillo.pedrojoya.pr015.extensions.inflate

abstract class AdapterViewBaseAdapter<out T, VH>
        protected constructor(protected val data: List<T>,
                              @LayoutRes private val layoutResId: Int) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: parent.inflate(layoutResId)
        @Suppress("UNCHECKED_CAST")
        val viewHolder: VH = convertView?.tag as? VH ?: onCreateViewHolder(itemView)
        itemView.tag = viewHolder
        onBindViewHolder(viewHolder, position)
        return itemView
    }

    protected abstract fun onCreateViewHolder(itemView: View): VH

    override fun getCount() = data.size

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int) = position.toLong()

    protected abstract fun onBindViewHolder(holder: VH, position: Int)

}
