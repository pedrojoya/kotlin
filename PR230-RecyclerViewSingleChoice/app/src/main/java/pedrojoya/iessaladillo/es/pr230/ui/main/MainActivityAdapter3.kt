package pedrojoya.iessaladillo.es.pr230.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*
import pedrojoya.iessaladillo.es.pr230.R
import pedrojoya.iessaladillo.es.pr230.base.StableIdListAdapter
import pedrojoya.iessaladillo.es.pr230.data.local.model.Student
import pedrojoya.iessaladillo.es.pr230.extensions.loadUrl

class MainActivityAdapter3 : StableIdListAdapter<Student, MainActivityAdapter3.ViewHolder>(object :
        DiffUtil.ItemCallback<Student>() {

    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name && oldItem.address == newItem.address

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.activity_main_item, parent, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item, position, selectionTracker?.isSelected(getKey(position)) ?: false)
    }

    inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                if (selectionTracker?.hasSelection() != true) {
                    selectionTracker?.select(getKey(adapterPosition))
                }
            }
        }

        fun bind(item: Student, position: Int, isSelected: Boolean) {
            itemView.isActivated = isSelected
            lblName.text = item.name
            lblAddress.text = item.address
            imgAvatar.loadUrl(item.photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
        }

    }

}
