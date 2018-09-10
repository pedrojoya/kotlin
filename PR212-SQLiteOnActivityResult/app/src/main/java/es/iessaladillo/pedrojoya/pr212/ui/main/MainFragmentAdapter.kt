package es.iessaladillo.pedrojoya.pr212.ui.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import es.iessaladillo.pedrojoya.pr212.R
import es.iessaladillo.pedrojoya.pr212.data.model.Student
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_main_item.*
import java.util.*

class MainFragmentAdapter : RecyclerView.Adapter<MainFragmentAdapter.ViewHolder>() {

    private var data: List<Student>? = ArrayList()
    private var onItemClickListener: OnItemClickListener? = null
    private val mDrawableBuilder: TextDrawable.IBuilder = TextDrawable.builder()
            .beginConfig()
            .width(100)
            .height(100)
            .toUpperCase()
            .endConfig()
            .round()
    private var mEmptyView: View? = null
    private val mObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            checkEmptyViewVisibility()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkEmptyViewVisibility()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkEmptyViewVisibility()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, student: Student, position: Int)
    }

    fun setData(data: List<Student>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_main_item, parent, false)
        val viewHolder = ViewHolder(itemView)
        itemView.setOnClickListener { v ->
            if (onItemClickListener != null) {
                onItemClickListener!!.onItemClick(v, getItemAtPosition(viewHolder.adapterPosition),
                        viewHolder.adapterPosition)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: MainFragmentAdapter.ViewHolder, position: Int) {
        viewHolder.bind(data!![position])
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun getItemAtPosition(position: Int): Student = data!![position]

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
        if (mEmptyView != null) {
            unregisterAdapterDataObserver(mObserver)
        }
        mEmptyView = emptyView
        registerAdapterDataObserver(mObserver)
    }

    private fun checkEmptyViewVisibility() {
        if (mEmptyView != null) {
            mEmptyView!!.visibility = if (itemCount == 0) View.VISIBLE else View.INVISIBLE
        }
    }

    fun onDestroy() {
        if (mEmptyView != null) {
            unregisterAdapterDataObserver(mObserver)
        }
    }

    inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(student: Student) {
            student.run {
                lblName.text = name
                lblGrade.text = grade
                lblAddress.text = address
                imgAvatar.setImageDrawable(mDrawableBuilder.build(name.substring(0, 1),
                        ColorGenerator.MATERIAL.getColor(name)))
            }
        }

    }

}
