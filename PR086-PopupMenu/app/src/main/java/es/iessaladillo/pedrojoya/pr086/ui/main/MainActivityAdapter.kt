package es.iessaladillo.pedrojoya.pr086.ui.main

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr086.data.local.model.Student
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

@Suppress("unused")
class MainActivityAdapter(data: List<Student> = emptyList()) :
        AdapterViewBaseAdapter<Student, MainActivityAdapter.ViewHolder>(data, R.layout.activity_main_item) {

    private var callListener: OnCallListener? = null
    private var sendMessageListener: OnSendMessageListener? = null

    override fun onCreateViewHolder(itemView: View): MainActivityAdapter.ViewHolder = ViewHolder(itemView)

    override fun onBindViewHolder(holder: MainActivityAdapter.ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    interface OnCallListener {
        fun onCall(view: View, student: Student, position: Int)
    }

    interface OnSendMessageListener {
        fun onSendMessage(view: View, student: Student, position: Int)
    }

    fun setOnCallListener(listener: OnCallListener) {
        callListener = listener
    }

    fun setOnCallListener(action: (view: View, student: Student, position: Int) -> Unit) {
        callListener = object: OnCallListener {
            override fun onCall(view: View, student: Student, position: Int) {
                action(view, student, position)
            }
        }
    }

    fun setOnSendMessageListener(listener: OnSendMessageListener) {
        sendMessageListener = listener
    }

    fun setOnSendMessageListener(action: (view: View, student: Student, position: Int) -> Unit) {
        sendMessageListener = object: OnSendMessageListener {
            override fun onSendMessage(view: View, student: Student, position: Int) {
                action(view, student, position)
            }
        }
    }

    inner class ViewHolder(override val containerView: View) : LayoutContainer {

        fun bind(student: Student, position: Int) {
            student.run {
                lblName.text = name
                lblPhone.text = phone
                lblAddress.text = address
                lblGrade.text = grade
                imgPopupMenu.setOnClickListener { view -> showPopup(view, student, position) }
            }
        }

        private fun showPopup(view: View, student: Student, position: Int) {
            PopupMenu(view.context, view).apply {
                inflate(R.menu.activity_main_item_popup)
                setOnMenuItemClickListener { menuItem -> onMenuItemClick(view, student, position, menuItem) }
            }.show()
        }

        private fun onMenuItemClick(view: View, student: Student, position: Int, menuItem: MenuItem): Boolean =
                when (menuItem.itemId) {
                    R.id.mnuCall -> {
                        callListener?.onCall(view, student, position)
                        true
                    }
                    R.id.mnuSendMessage -> {
                        sendMessageListener?.onSendMessage(view, student, position)
                        true
                    }
                    else -> false
                }

    }

}
