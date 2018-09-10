package es.iessaladillo.pedrojoya.pr194.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import es.iessaladillo.pedrojoya.pr194.R
import es.iessaladillo.pedrojoya.pr194.data.model.Student
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*
import es.iessaladillo.pedrojoya.pr194.extensions.loadUrl
import kotlin.properties.Delegates

class MainActivityAdapter(_data: List<Student> = listOf()) : BaseAdapter() {

    var data: List<Student> by Delegates.observable(_data) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.activity_main_item, parent, false)
        val viewHolder: ViewHolder = itemView.tag as? ViewHolder ?: onCreateViewHolder(itemView)
        itemView.tag = viewHolder
        onBindViewHolder(viewHolder, position)
        return itemView
    }

    override fun getItem(position: Int): Any = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    private fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    private fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }

    override fun getCount(): Int = data.size

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
