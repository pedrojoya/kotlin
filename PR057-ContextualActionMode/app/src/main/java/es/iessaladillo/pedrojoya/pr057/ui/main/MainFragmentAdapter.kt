package es.iessaladillo.pedrojoya.pr057.ui.main

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import es.iessaladillo.pedrojoya.pr057.R
import es.iessaladillo.pedrojoya.pr057.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr057.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr057.data.local.model.Student
import es.iessaladillo.pedrojoya.pr057.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class MainFragmentAdapter : BaseListAdapter<Student, MainFragmentAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return TextUtils.equals(oldItem.name, newItem.name)
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return (TextUtils.equals(oldItem.name, newItem.name) && TextUtils.equals(
                oldItem.address, newItem.address) && TextUtils.equals(
                oldItem.grade, newItem.grade)
                && oldItem.isRepeater == newItem.isRepeater)
    }
}) {

    var onShowAssignmentsListener: ((Int) -> Unit)? = null
    var onShowMarksListener: ((Int) -> Unit)? = null
    var onShowContextualModeListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_main_item, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View) : BaseViewHolder(containerView,
            onItemClickListener), LayoutContainer {

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
            lblRepeater.visibility = if (student.isRepeater) View.VISIBLE else View.INVISIBLE
            btnAssignments.setOnClickListener { onShowAssignmentsListener?.invoke(adapterPosition) }
            btnMarks.setOnClickListener { onShowMarksListener?.invoke(adapterPosition) }
            imgPopupMenu.setOnClickListener { onShowContextualModeListener?.invoke(adapterPosition) }
        }

    }

}
