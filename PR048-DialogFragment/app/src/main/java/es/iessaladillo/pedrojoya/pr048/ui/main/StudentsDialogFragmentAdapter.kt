package es.iessaladillo.pedrojoya.pr048.ui.main

import android.view.View
import es.iessaladillo.pedrojoya.pr048.R
import es.iessaladillo.pedrojoya.pr048.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr048.data.Student
import es.iessaladillo.pedrojoya.pr048.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*
import java.util.*

class StudentsDialogFragmentAdapter(students: ArrayList<Student>) :
        AdapterViewBaseAdapter<Student, ViewHolder>(students, R.layout.activity_main_item) {

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

class ViewHolder(override val containerView: View): LayoutContainer {

    fun bind(student: Student) {
        imgAvatar.loadUrl(student.photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
        lblName.text = student.name
        lblAddress.text = student.address
    }

}
