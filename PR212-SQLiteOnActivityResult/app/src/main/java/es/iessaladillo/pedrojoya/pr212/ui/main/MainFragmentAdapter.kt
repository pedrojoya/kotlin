package es.iessaladillo.pedrojoya.pr212.ui.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import es.iessaladillo.pedrojoya.pr212.R
import es.iessaladillo.pedrojoya.pr212.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr212.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr212.data.local.model.Student
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*

class MainFragmentAdapter : BaseListAdapter<Student, MainFragmentAdapter.ViewHolder>(diffUtilItemCallback) {

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

    override fun onBindViewHolder(viewHolder: MainFragmentAdapter.ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    companion object {

        val diffUtilItemCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
                oldItem.name == newItem.name && oldItem.grade == newItem.grade &&
                        oldItem.address == newItem.address
        }

    }

    inner class ViewHolder(override val containerView: View) :
            BaseViewHolder(containerView, onItemClickListener, onItemLongClickListener), LayoutContainer {

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

}
