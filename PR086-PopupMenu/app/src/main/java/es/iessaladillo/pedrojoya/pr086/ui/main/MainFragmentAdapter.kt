package es.iessaladillo.pedrojoya.pr086.ui.main

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr086.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import es.iessaladillo.pedrojoya.pr086.extensions.enableIcons
import es.iessaladillo.pedrojoya.pr086.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

// Automatic SAM conversion only for Java interfaces

fun MainFragmentAdapter.setOnShowAssignmentsListener(action: (Int) -> Unit) {
    onShowAssignmentsListener = object : MainFragmentAdapter.OnShowAssignmentsListener {
        override fun onShowAssignments(position: Int) {
            action(position)
        }
    }
}

fun MainFragmentAdapter.setOnShowMarksListener(action: (Int) -> Unit) {
    onShowMarksListener = object : MainFragmentAdapter.OnShowMarksListener {
        override fun onShowMarks(position: Int) {
            action(position)
        }
    }
}

fun MainFragmentAdapter.setOnCallListener(action: (Int) -> Unit) {
    onCallListener = object : MainFragmentAdapter.OnCallListener {
        override fun onCall(position: Int) {
            action(position)
        }
    }
}

fun MainFragmentAdapter.setOnSendMessageListener(action: (Int) -> Unit) {
    onSendMessageListener = object : MainFragmentAdapter.OnSendMessageListener {
        override fun onSendMessage(position: Int) {
            action(position)
        }
    }
}

class MainFragmentAdapter : BaseListAdapter<Student, MainFragmentAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name &&
                    oldItem.address == newItem.address &&
                    oldItem.grade== newItem.grade &&
                    oldItem.repeater == newItem.repeater
}) {

    var onShowAssignmentsListener: OnShowAssignmentsListener? = null
    var onShowMarksListener: OnShowMarksListener? = null
    var onCallListener: OnCallListener? = null
    var onSendMessageListener: OnSendMessageListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_main_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnShowAssignmentsListener {
        fun onShowAssignments(position: Int)
    }

    interface OnShowMarksListener {
        fun onShowMarks(position: Int)
    }

    interface OnCallListener {
        fun onCall(position: Int)
    }

    interface OnSendMessageListener {
        fun onSendMessage(position: Int)
    }

    inner class ViewHolder(override val containerView: View) : BaseViewHolder(containerView, onItemClickListener), LayoutContainer {

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
            btnAssignments.setOnClickListener { v ->
                if (onShowAssignmentsListener != null) {
                    onShowAssignmentsListener!!.onShowAssignments(adapterPosition)
                }
            }
            btnMarks.setOnClickListener { v ->
                if (onShowMarksListener != null) {
                    onShowMarksListener!!.onShowMarks(adapterPosition)
                }
            }
            imgPopupMenu.setOnClickListener { v -> showPopup(adapterPosition, v) }
        }

        private fun onMenuItemClick(position: Int, menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.mnuCall -> onCallListener!!.onCall(position)
                R.id.mnuSendMessage -> onSendMessageListener!!.onSendMessage(position)
                else -> return false
            }
            return true
        }

        private fun showPopup(position: Int, v: View) {
            PopupMenu(v.context, v).run {
                inflate(R.menu.activity_main_item_popup)
                setOnMenuItemClickListener { menuItem -> onMenuItemClick(position, menuItem) }
                enableIcons()
                show()
            }
        }

    }

}
