package es.iessaladillo.pedrojoya.pr178.ui.main

import android.view.View
import android.view.ViewGroup
import es.iessaladillo.pedrojoya.pr178.R
import es.iessaladillo.pedrojoya.pr178.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr178.base.BaseViewHolder
import es.iessaladillo.pedrojoya.pr178.data.local.model.Student
import es.iessaladillo.pedrojoya.pr178.extensions.inflate
import es.iessaladillo.pedrojoya.pr178.extensions.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

class MainActivityAdapter(data: ArrayList<Student>) : BaseListAdapter<Student, MainActivityAdapter.ViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.activity_main_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View) :
            BaseViewHolder(containerView, onItemClickListener, onItemLongClickListener), LayoutContainer {


        fun bind(item: Student) {
            item.run {
                lblName.text = name
                lblAddress.text = address
                imgAvatar.loadUrl(photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
            }
        }

    }

}
