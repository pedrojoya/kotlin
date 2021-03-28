package pedrojoya.iessaladillo.es.pr230.ui.main

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*
import pedrojoya.iessaladillo.es.pr230.R
import pedrojoya.iessaladillo.es.pr230.data.local.model.Student
import pedrojoya.iessaladillo.es.pr230.extensions.loadUrl

class MainActivityAdapter2 : ListAdapter<Student, MainActivityAdapter2.ViewHolder>(object : DiffUtil.ItemCallback<Student>() {

    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name && oldItem.address == newItem.address

}) {

    private var onItemClickListener: ((position: Int) -> Unit)? = null
    var selectionTracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_main_item, parent, false), onItemClickListener)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item, position, selectionTracker?.isSelected(item.id) ?: false)
    }

    fun setOnItemClickListener(listener: ((Int) -> Unit)) {
        onItemClickListener = listener
    }

    public override fun getItem(position: Int): Student {
        return super.getItem(position)
    }

    fun getKey(position: Int): Long = getItem(position).id

    fun getPosition(key: Long): Int {
        val position = currentList.indexOfFirst { it.id == key }
        return if (position >= 0) position else NO_POSITION
    }

    private class KeyProvider(
            private val adapter: MainActivityAdapter2
    ) : ItemKeyProvider<Long>(SCOPE_MAPPED) {
        override fun getKey(position: Int): Long = adapter.getKey(position)
        override fun getPosition(key: Long): Int = adapter.getPosition(key)
    }

    class ItemDetails : ItemDetailsLookup.ItemDetails<Long>() {
        var pos = 0
        var key: Long? = null
        override fun getPosition() = pos
        override fun getSelectionKey() = key
        override fun inSelectionHotspot(e: MotionEvent) = true
    }

    private class DetailsLookup(
            private val recyclerView: RecyclerView
    ) : ItemDetailsLookup<Long>() {
        override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? =
                recyclerView.findChildViewUnder(e.x, e.y)
                        ?.let { (recyclerView.getChildViewHolder(it) as ViewHolder).details }
    }

    class ViewHolder(override val containerView: View,
                     onItemClickListener: ((Int) -> Unit)? = null) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            onItemClickListener?.let {
                itemView.setOnClickListener { v ->
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onItemClickListener(adapterPosition)
                    }
                }
            }
        }

        val details: ItemDetails = ItemDetails()

        fun bind(item: Student, position: Int, isSelected: Boolean) {
            itemView.isActivated = isSelected
            details.pos = position
            details.key = item.id
            lblName.text = item.name
            lblAddress.text = item.address
            imgAvatar.loadUrl(item.photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
        }

    }

    companion object {

        fun newSelectionTrackerBuilder(selectionId: String, recyclerView: RecyclerView,
                                       adapter: MainActivityAdapter2): SelectionTracker.Builder<Long> =
                SelectionTracker.Builder<Long>(
                        selectionId,
                        recyclerView,
                        KeyProvider(adapter),
                        DetailsLookup(recyclerView),
                        StorageStrategy.createLongStorage())


    }

}
