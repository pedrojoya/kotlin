package pedrojoya.iessaladillo.es.pr231.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.synthetic.main.activity_main_item.*
import pedrojoya.iessaladillo.es.pr231.base.BaseListAdapter
import pedrojoya.iessaladillo.es.pr231.base.BaseViewHolder
import pedrojoya.iessaladillo.es.pr231.base.PositionalDetailsLookup
import pedrojoya.iessaladillo.es.pr231.base.PositionalItemDetails
import pedrojoya.iessaladillo.es.pr231.data.local.model.Student
import pedrojoya.iessaladillo.es.pr231.extensions.loadUrl
import pedrojoya.iessaladillo.es.pr331.R

class MainActivityAdapter : BaseListAdapter<Student, MainActivityAdapter.ViewHolder>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_item, parent, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Es muy importante que la posición sea convertida a long, o de lo contrario
        // isSelected() devolverá false.
        viewHolder.bind(getItem(position),
                selectionTracker?.isSelected(position.toLong())?:false)
    }

    inner class ViewHolder(itemView: View) :
            BaseViewHolder(itemView, getOnItemClickListener()), PositionalDetailsLookup.DetailsProvider {

        override fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> {
            return PositionalItemDetails(adapterPosition)
        }

        fun bind(student: Student, selected: Boolean) {
            itemView.isActivated = selected
            lblName.text = student.name
            lblAddress.text = student.address
            imgAvatar.loadUrl(student.photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
        }

    }

    companion object {

        private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Student>() {

            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem.name == newItem.name && oldItem.address == newItem.address

        }

    }

}
