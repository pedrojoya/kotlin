package pedrojoya.iessaladillo.es.pr201.ui.main

import android.support.v7.util.DiffUtil
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*
import pedrojoya.iessaladillo.es.pr201.R
import pedrojoya.iessaladillo.es.pr201.base.BaseListAdapter
import pedrojoya.iessaladillo.es.pr201.base.BaseViewHolder
import pedrojoya.iessaladillo.es.pr201.data.Student
import pedrojoya.iessaladillo.es.pr201.extensions.inflate
import pedrojoya.iessaladillo.es.pr201.extensions.loadUrl

class MainActivityAdapter(data: List<Student>) : BaseListAdapter<Student, ViewHolder>(data) {

    internal class DiffStudentsCallback(private val oldStudents: List<Student>, private val newStudents: List<Student>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldStudents.size

        override fun getNewListSize(): Int = newStudents.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldStudents[oldItemPosition].id == newStudents[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldStudents[oldItemPosition].name == newStudents[newItemPosition].name &&
                oldStudents[oldItemPosition].address == newStudents[newItemPosition].address

    }

    override fun submitList(newList: List<Student>) {
        val diffResult = DiffUtil.calculateDiff(
                DiffStudentsCallback(data, newList))
        data = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.activity_main_item), this)

}

class ViewHolder(override val containerView: View, adapter: MainActivityAdapter) :
        BaseViewHolder<Student>(containerView, adapter), LayoutContainer {


    override fun bind(item: Student) {
        with(item) {
            lblName.text = name
            lblAddress.text = address
            imgAvatar.loadUrl(photoUrl, R.drawable.ic_person_black_24dp, R.drawable
                    .ic_person_black_24dp)
        }
    }

}


