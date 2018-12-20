package es.iessaladillo.pedrojoya.pr195.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import es.iessaladillo.pedrojoya.pr195.R
import es.iessaladillo.pedrojoya.pr195.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr195.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr195.data.model.Student
import es.iessaladillo.pedrojoya.pr195.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class MainFragmentAdapter : BaseListAdapter<Student, MainFragmentAdapter.ViewHolder>(diffUtilItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_main_item,
                    parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View): BaseViewHolder(containerView, onItemClickListener, onItemLongClickListener), LayoutContainer {

        fun bind(student: Student) {
            student.run {
                lblName.text = name
                lblGrade.text = grade
                lblAge.text = lblAge.context.resources
                        .getQuantityString(R.plurals.main_activity_adapter_years, age, age)
                imgPhoto.loadUrl(photo, R.drawable.placeholder, R.drawable.ic_person_add_black_24dp)
                lblAge.setTextColor(
                        if (age < 18) ContextCompat.getColor(lblAge.context, R.color.accent)
                        else ContextCompat.getColor(lblAge.context, R.color.primary_text))
                lblRepeater.visibility = if (isRepeater) View.VISIBLE else View.INVISIBLE
            }
        }

    }

    companion object {

        private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.photo == newItem.photo &&
                        oldItem.grade == newItem.grade &&
                        oldItem.address == newItem.address &&
                        oldItem.isRepeater == newItem.isRepeater
            }
        }

    }

}
