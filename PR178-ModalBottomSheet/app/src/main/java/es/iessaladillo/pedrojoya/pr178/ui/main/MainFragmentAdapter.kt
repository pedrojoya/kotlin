package es.iessaladillo.pedrojoya.pr178.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr178.R
import es.iessaladillo.pedrojoya.pr178.data.local.model.Student
import es.iessaladillo.pedrojoya.pr178.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class MainFragmentAdapter internal constructor() : ListAdapter<Student, MainFragmentAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name && oldItem.address == newItem.address && oldItem
                    .photoUrl == newItem.photoUrl
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_item, parent, false))

    override fun onBindViewHolder(viewHolder: MainFragmentAdapter.ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    public override fun getItem(position: Int): Student {
        return super.getItem(position)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    showBottomSheetDialogFragment(getItem(adapterPosition))
                }
            }
        }

        fun bind(student: Student) {
            lblName.text = student.name
            lblAddress.text = student.address
            imgAvatar.loadUrl(student.photoUrl, R.drawable.ic_person_black_24dp)
        }

        private fun showBottomSheetDialogFragment(student: Student) {
            MenuBottomSheetDialogFragment.newInstance(student).show(
                    (itemView.context as AppCompatActivity).supportFragmentManager,
                    MenuBottomSheetDialogFragment::class.java.simpleName)
        }

    }

}
