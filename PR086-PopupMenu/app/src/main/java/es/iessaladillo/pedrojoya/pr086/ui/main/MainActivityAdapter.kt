package es.iessaladillo.pedrojoya.pr086.ui.main

import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr086.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import es.iessaladillo.pedrojoya.pr086.extensions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

fun MainActivityAdapter.setOnCallListener(action: (view: View, position: Int) -> Unit) {
    callListener = object: MainActivityAdapter.OnCallListener {
        override fun onCall(view: View, position: Int) {
            action(view, position)
        }
    }
}

fun MainActivityAdapter.setOnSendMessageListener(action: (view: View, position: Int) -> Unit) {
    sendMessageListener = object: MainActivityAdapter.OnSendMessageListener {
        override fun onSendMessage(view: View, position: Int) {
            action(view, position)
        }
    }
}

class MainActivityAdapter : BaseListAdapter<Student, MainActivityAdapter.ViewHolder>
(diffUtilItemCallback) {

    var callListener: MainActivityAdapter.OnCallListener? = null
    var sendMessageListener: MainActivityAdapter.OnSendMessageListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.activity_main_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(override val containerView: View): BaseViewHolder(containerView, onItemClickListener, onItemLongClickListener), LayoutContainer {

        fun bind(student: Student, position: Int) {
            student.run {
                lblName.text = name
                lblPhone.text = phone
                lblAddress.text = address
                lblGrade.text = grade
                imgPopupMenu.setOnClickListener { view -> showPopup(view, position) }
            }
        }

        private fun showPopup(view: View, position: Int) {
            PopupMenu(view.context, view).apply {
                inflate(R.menu.activity_main_item_popup)
                setOnMenuItemClickListener { menuItem -> onMenuItemClick(view, position, menuItem) }
            }.show()
        }

        private fun onMenuItemClick(view: View, position: Int, menuItem: MenuItem): Boolean =
                when (menuItem.itemId) {
                    R.id.mnuCall -> {
                        callListener?.onCall(view, position)
                        true
                    }
                    R.id.mnuSendMessage -> {
                        sendMessageListener?.onSendMessage(view, position)
                        true
                    }
                    else -> false
                }

    }

    interface OnCallListener {
        fun onCall(view: View, position: Int)
    }

    interface OnSendMessageListener {
        fun onSendMessage(view: View, position: Int)
    }

    companion object {

        private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.grade == newItem.grade &&
                        oldItem.address == newItem.address &&
                        oldItem.grade == newItem.grade
            }
        }

    }

}
