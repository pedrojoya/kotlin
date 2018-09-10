package es.iessaladillo.pedrojoya.pr012.ui.main

import android.view.View
import androidx.core.content.ContextCompat
import es.iessaladillo.pedrojoya.pr012.R
import es.iessaladillo.pedrojoya.pr012.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr012.data.local.model.Student
import es.iessaladillo.pedrojoya.pr012.extensions.getQuantityString
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

@Suppress("unused")
class MainActivityAdapter(data: List<Student> = emptyList())
    : AdapterViewBaseAdapter<Student, MainActivityAdapter.ViewHolder>(data, R.layout.activity_main_item) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    //
    // For compatibility with Java: We create de interfaces instead of using only lambdas.
    // SAM only works for code created in Java.
    //

    private var onCallListener: CallListener? = null
    private var onMarksListener: ShowMarksListener? = null

    fun setOnCallListener(listener: ((view: View, student: Student, position: Int) -> Unit)) {
        onCallListener = object: CallListener {
            override fun onCall(view: View, student: Student, position: Int) {
                listener.invoke(view, student, position)
            }
        }
    }

    fun setOnMarksListener(listener: ((view: View, student: Student, position: Int) -> Unit)) {
        onMarksListener = object: ShowMarksListener {
            override fun onShowMarks(view: View, student: Student, position: Int) {
                listener.invoke(view, student, position)
            }

        }
    }

    fun setOnCallListener(listener: CallListener) {
        onCallListener = listener
    }

    fun setOnMarksListener(listener: ShowMarksListener) {
        onMarksListener = listener
    }

    interface CallListener {
        fun onCall(view: View, student: Student, position: Int)
    }

    interface ShowMarksListener {
        fun onShowMarks(view: View, student: Student, position: Int)
    }

    //
    // End for compatibility with Java
    //

    inner class ViewHolder(override val containerView: View): LayoutContainer {

        fun bind(student: Student, position: Int) {
            student.run {
                imgPhoto.setImageResource(photo)
                lblName.text = name
                lblGrade.text = lblGrade.context
                        .getString(R.string.main_activity_grade_and_level, grade, level)
                lblAge.text = lblAge.context.getQuantityString(R.plurals.main_activity_age, age, age)
                lblAge.setTextColor(if (age < 18)
                    ContextCompat.getColor(lblAge.context, R.color.accent)
                else
                    ContextCompat.getColor(lblAge.context, R.color.primary_text))
                lblRepeater.visibility = if (isRepeater) View.VISIBLE else View.INVISIBLE
                btnCall.setOnClickListener { onCallListener?.onCall(it, student, position) }
                btnMarks.setOnClickListener { onMarksListener?.onShowMarks(it, student, position) }
            }
        }

    }

}
