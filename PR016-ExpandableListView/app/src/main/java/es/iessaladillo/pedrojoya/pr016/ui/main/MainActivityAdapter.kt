package es.iessaladillo.pedrojoya.pr016.ui.main

import android.view.View
import androidx.core.content.ContextCompat
import es.iessaladillo.pedrojoya.pr016.ADULT_AGE
import es.iessaladillo.pedrojoya.pr016.R
import es.iessaladillo.pedrojoya.pr016.base.StardardExpandableListAdapter
import es.iessaladillo.pedrojoya.pr016.data.local.model.Student
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_child.*
import kotlinx.android.synthetic.main.activity_main_group.*
import java.util.*

class MainActivityAdapter(
        private val groups: ArrayList<String>,
        private val children: ArrayList<ArrayList<Student>>)
    : StardardExpandableListAdapter<String, Student, MainActivityAdapter.GroupViewHolder, MainActivityAdapter.ChildViewHolder>(
        groups, children, R.layout.activity_main_group, R.layout.activity_main_child) {

    override fun onCreateChildViewHolder(itemView: View) = ChildViewHolder(itemView)

    override fun onBindChildViewHolder(viewHolder: ChildViewHolder, groupPosition: Int, childPosition: Int) {
        viewHolder.bind(children[groupPosition][childPosition])
    }

    override fun onBindGroupViewHolder(viewHolder: GroupViewHolder, groupPosition: Int, expanded: Boolean) {
        viewHolder.bind(groups[groupPosition], getChildrenCount(groupPosition), expanded)
    }

    override fun onCreateGroupViewHolder(itemView: View) = GroupViewHolder(itemView)

    class ChildViewHolder(override val containerView: View) : LayoutContainer {

        fun bind(student: Student) {
            lblName.text = student.name
            lblGrade.text = student.grade
            lblName.setTextColor(
                    if (student.age < ADULT_AGE) ContextCompat.getColor(lblName.context, R.color.primary_400)
                    else ContextCompat.getColor(lblName.context, R.color.primary_text)
            )
        }

    }

    class GroupViewHolder(override val containerView: View) : LayoutContainer {

        fun bind(group: String, childrenCount: Int, isExpanded: Boolean) {
            lblLevelHeader.text = group
            // Show or hide columns header and change indicator.
            if (childrenCount == 0) {
                imgIndicator.visibility = View.INVISIBLE
                llColumnsHeader.visibility = View.GONE
            } else {
                imgIndicator.visibility = View.VISIBLE
                if (isExpanded) {
                    imgIndicator.setImageResource(R.drawable.ic_expand_less_white_24dp)
                    llColumnsHeader.visibility = View.VISIBLE
                } else {
                    imgIndicator.setImageResource(R.drawable.ic_expand_more_white_24dp)
                    llColumnsHeader.visibility = View.GONE
                }
            }
        }

    }

}
