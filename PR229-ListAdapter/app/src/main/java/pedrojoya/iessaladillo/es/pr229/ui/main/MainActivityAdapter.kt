package pedrojoya.iessaladillo.es.pr229.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*
import pedrojoya.iessaladillo.es.pr229.R
import pedrojoya.iessaladillo.es.pr229.base.BaseListAdapter
import pedrojoya.iessaladillo.es.pr229.base.BaseViewHolder
import pedrojoya.iessaladillo.es.pr229.data.local.model.Student
import pedrojoya.iessaladillo.es.pr229.extensions.inflate
import pedrojoya.iessaladillo.es.pr229.extensions.loadUrl

class MainActivityAdapter : BaseListAdapter<Student, MainActivityAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem.name == newItem.name && oldItem.address == newItem.address &&
                            oldItem.photoUrl == newItem.photoUrl
        }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.activity_main_item))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View) : BaseViewHolder(containerView, onItemClickListener, onItemLongClickListener), LayoutContainer {

        fun bind(item: Student) {
            item.run {
                lblName.text = name
                lblAddress.text = address
                imgAvatar.loadUrl(photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
            }
        }

    }

}
