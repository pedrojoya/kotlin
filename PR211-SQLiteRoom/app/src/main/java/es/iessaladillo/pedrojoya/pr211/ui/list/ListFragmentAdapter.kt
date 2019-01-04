package es.iessaladillo.pedrojoya.pr211.ui.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.data.local.model.Student
import es.iessaladillo.pedrojoya.pr211.ui.student.StudentFragment
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class ListFragmentAdapter : ListAdapter<Student, ListFragmentAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
            oldItem.name == newItem.name && oldItem.grade == newItem.grade &&
                    oldItem.address == newItem.address
}) {

    private val drawableBuilder: TextDrawable.IBuilder = TextDrawable.builder()
            .beginConfig()
            .width(100)
            .height(100)
            .toUpperCase()
            .endConfig()
            .round()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_main_item, parent,
                    false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    public override fun getItem(position: Int): Student {
        return super.getItem(position)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    navigateToEditStudent(getItem(adapterPosition))
                }
            }
        }

        fun bind(student: Student) {
            student.run {
                lblName.text = name
                lblGrade.text = grade
                lblAddress.text = address
                imgAvatar.setImageDrawable(drawableBuilder.build(name.substring(0, 1),
                        ColorGenerator.MATERIAL.getColor(name)))
            }
        }

        private fun navigateToEditStudent(student: Student) {
            (itemView.context as AppCompatActivity).supportFragmentManager.commit {
                replace(R.id.flContent, StudentFragment.newInstance(student.id),
                        StudentFragment::class.java.simpleName)
                addToBackStack(StudentFragment::class.java.simpleName)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }
        }

    }

}
