package es.iessaladillo.pedrojoya.pr086.main

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import es.iessaladillo.pedrojoya.pr086.R
import es.iessaladillo.pedrojoya.pr086.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr086.data.Student
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

class MainActivityAdapter(data: List<Student>, private val listener: Callback) :
        AdapterViewBaseAdapter<Student, ViewHolder>(data, R.layout.activity_main_item) {

    // Communication interface
    interface Callback {

        fun onCall(student: Student)

        fun onSendMessage(student: Student)
    }

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView, listener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

class ViewHolder(override val containerView: View,
                 private val listener: MainActivityAdapter.Callback) : LayoutContainer {

    fun bind(student: Student) {
        lblName.text = student.name
        lblPhone.text = student.phone
        lblAddress.text = student.address
        lblGrade.text = student.grade
        imgPopupMenu.setOnClickListener { v -> showPopup(student, v) }
    }

    private fun showPopup(student: Student, v: View) {
        with (PopupMenu(v.context, v)) {
            inflate(R.menu.activity_main_item_popup)
            setOnMenuItemClickListener { menuItem -> onMenuItemClick(student, menuItem) }
            show()
        }
    }

    private fun onMenuItemClick(student: Student, menuItem: MenuItem): Boolean =
        when (menuItem.itemId) {
            R.id.mnuCall -> { listener.onCall(student); true }
            R.id.mnuSendMessage -> { listener.onSendMessage(student); true }
            else -> false
        }

}

