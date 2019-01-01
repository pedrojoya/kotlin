package es.iessaladillo.pedrojoya.pr105.ui.main.option2.tab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.data.local.model.Student
import es.iessaladillo.pedrojoya.pr105.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_option2_tab1_item.*

class Option2Tab1Adapter : ListAdapter<Student,
        Option2Tab1Adapter.ViewHolder>(object : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name && oldItem.address == newItem.address && oldItem
                    .photoUrl == newItem.photoUrl

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_option2_tab1_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(student: Student) {
            student.run {
                lblName.text = name
                lblAddress.text = address
                imgAvatar.loadUrl(photoUrl)
            }
        }

    }

}

