package pedrojoya.iessaladillo.es.pr106.ui.main

import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*
import pedrojoya.iessaladillo.es.pr106.R
import pedrojoya.iessaladillo.es.pr106.base.BaseListAdapter
import pedrojoya.iessaladillo.es.pr106.base.BaseViewHolder
import pedrojoya.iessaladillo.es.pr106.data.local.model.Student
import pedrojoya.iessaladillo.es.pr106.extensions.inflate
import pedrojoya.iessaladillo.es.pr106.extensions.loadUrl
import pedrojoya.iessaladillo.es.pr106.ui.main.MainActivityAdapter.ViewHolder

class MainActivityAdapter(data: ArrayList<Student> = ArrayList()) : BaseListAdapter<Student, ViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.activity_main_item))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View): BaseViewHolder(containerView, onItemClickListener, onItemLongClickListener), LayoutContainer {

        fun bind(item: Student) {
            item.run {
                lblName.text = name
                lblAddress.text = address
                imgAvatar.loadUrl(photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
            }
        }

    }
}



