package es.iessaladillo.pedrojoya.pr086.ui.main

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import es.iessaladillo.pedrojoya.pr086.extensions.enableIcons
import es.iessaladillo.pedrojoya.pr086.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class MainFragmentAdapter(val viewModel: MainFragmentViewModel) : ListAdapter<Student,
        MainFragmentAdapter.ViewHolder>
(object :
        DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name &&
                    oldItem.address == newItem.address &&
                    oldItem.grade == newItem.grade &&
                    oldItem.repeater == newItem.repeater
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_main_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    public override fun getItem(position: Int): Student {
        return super.getItem(position)
    }

    inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener { viewModel.showStudent(getItem(adapterPosition)) }
            btnAssignments.setOnClickListener { viewModel.showAssignments(getItem(adapterPosition)) }
            btnMarks.setOnClickListener { viewModel.showMarks(getItem(adapterPosition)) }
            imgPopupMenu.setOnClickListener { v -> showPopup(v) }
        }

        fun bind(student: Student) {
            lblName.text = student.name
            lblGrade.text = student.grade
            lblAge.text = lblAge.context
                    .resources
                    .getQuantityString(R.plurals.main_adapter_years, student.age,
                            student.age)
            imgPhoto.loadUrl(student.photo, R.drawable.placeholder)
            lblAge.setTextColor(if (student.age < 18)
                ContextCompat.getColor(lblAge.context,
                        R.color.accent)
            else
                ContextCompat.getColor(lblAge.context,
                        R.color.primary_text))
            lblRepeater.visibility = if (student.repeater) View.VISIBLE else View.INVISIBLE

        }

        private fun showPopup(v: View) {
            PopupMenu(v.context, v).run {
                inflate(R.menu.activity_main_item_popup)
                setOnMenuItemClickListener(this@ViewHolder::onMenuItemClick)
                enableIcons()
                show()
            }
        }

        private fun onMenuItemClick(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.mnuCall -> viewModel.call(getItem(adapterPosition))
                R.id.mnuSendMessage -> viewModel.sendMessage(getItem(adapterPosition))
                else -> return false
            }
            return true
        }

    }

}
