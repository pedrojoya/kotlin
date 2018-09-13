package es.iessaladillo.pedrojoya.pr083.ui.main

import android.view.View
import androidx.core.content.ContextCompat
import es.iessaladillo.pedrojoya.pr083.R
import es.iessaladillo.pedrojoya.pr083.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr083.data.model.Student
import es.iessaladillo.pedrojoya.pr083.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

class MainActivityAdapter(_data: List<Student> = listOf()) :
        AdapterViewBaseAdapter<Student, MainActivityAdapter.ViewHolder>(_data, R.layout.activity_main_item) {

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(override val containerView: View) : LayoutContainer {

        fun bind(student: Student) {
            student.run {
                lblName.text = name
                lblGrade.text = grade
                lblAge.text = lblAge.context.resources
                        .getQuantityString(R.plurals.main_activity_adapter_years, age, age)
                imgPhoto.loadUrl(photo, R.drawable.placeholder, R.drawable.placeholder)
                lblAge.setTextColor(
                        if (age < 18) ContextCompat.getColor(lblAge.context, R.color.accent)
                        else ContextCompat.getColor(lblAge.context, R.color.primary_text))
                lblRepeater.visibility = if (isRepeater) View.VISIBLE else View.INVISIBLE
            }
        }

    }

}

