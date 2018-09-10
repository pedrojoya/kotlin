package es.iessaladillo.pedrojoya.pr211.ui.main


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.data.model.Student
import es.iessaladillo.pedrojoya.pr211.extensions.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class MainFragmentAdapter : ListAdapter<Student, MainFragmentAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: OnItemClickListener? = null
    private var listEmptyView: View? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, student: Student, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentAdapter.ViewHolder {
        val itemView = parent.inflate(R.layout.fragment_main_item)
        val viewHolder = MainFragmentAdapter.ViewHolder(itemView)
        itemView.setOnClickListener { v ->
            onItemClickListener?.apply {
                onItemClick(v, getItem(viewHolder.adapterPosition), viewHolder.adapterPosition)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: MainFragmentAdapter.ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    override fun submitList(list: List<Student>?) {
        checkEmptyViewVisibility(list?.size ?: 0)
        super.submitList(list)
    }

    fun getItemAtPosition(position: Int): Student {
        return getItem(position)
    }

    @Suppress("unused")
// For compatibility with Java
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    // For Kotlin lambdas
    fun setOnItemClickListener(action: (View, Student, Int) -> Unit) {
        onItemClickListener = object: OnItemClickListener {
            override fun onItemClick(view: View, student: Student, position: Int) {
                action(view, student, position)
            }

        }
    }

    fun setEmptyView(emptyView: View) {
        listEmptyView = emptyView
    }

    private fun checkEmptyViewVisibility(count: Int) {
        listEmptyView?.apply {
            visibility = if (count == 0) View.VISIBLE else View.INVISIBLE
        }
    }

    class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val drawableBuilder: TextDrawable.IBuilder =
                TextDrawable.builder()
                        .beginConfig()
                        .width(100)
                        .height(100)
                        .toUpperCase()
                        .endConfig()
                        .round()

        fun bind(student: Student) {
            student.run {
                lblName.text = name
                lblGrade.text = grade
                lblAddress.text = address
                imgAvatar.setImageDrawable(drawableBuilder.build(name.substring(0, 1),
                        ColorGenerator.MATERIAL.getColor(name)))
            }
        }

    }

    companion object {

        // DiffCallback for get differences between old and new data list.
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Student>() {

            // Student properties may have changed if reloaded from the DB, but ID is fixed
            override fun areItemsTheSame(oldStudent: Student, newStudent: Student): Boolean =
                    oldStudent.id == newStudent.id

            override fun areContentsTheSame(oldStudent: Student,
                                            newStudent: Student): Boolean {
                return oldStudent.name == newStudent.name &&
                        oldStudent.address == newStudent.address &&
                        oldStudent.grade == newStudent.grade
            }
        }
    }

}
