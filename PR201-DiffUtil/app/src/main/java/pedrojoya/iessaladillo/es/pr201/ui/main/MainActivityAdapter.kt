package pedrojoya.iessaladillo.es.pr201.ui.main

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.synthetic.main.activity_main_item.view.*
import pedrojoya.iessaladillo.es.pr201.R
import pedrojoya.iessaladillo.es.pr201.base.BaseAdapter
import pedrojoya.iessaladillo.es.pr201.data.local.model.Student
import pedrojoya.iessaladillo.es.pr201.extensions.loadUrl

class MainActivityAdapter(data: List<Student>) :
        BaseAdapter<Student>(data, R.layout.activity_main_item, diffUtilItemCallback) {

    override fun View.bind(item: Student) {
        item.run {
            lblName.text = name
            lblAddress.text = address
            imgAvatar.loadUrl(photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
        }
    }

    companion object {

        private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }

    }

}


