package pedrojoya.iessaladillo.es.pr229.ui.main

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.synthetic.main.activity_main_item.view.*
import pedrojoya.iessaladillo.es.pr229.R
import pedrojoya.iessaladillo.es.pr229.base.BaseAdapter
import pedrojoya.iessaladillo.es.pr229.data.local.model.Student
import pedrojoya.iessaladillo.es.pr229.extensions.loadUrl

class MainActivityAdapter : BaseAdapter<Student>(R.layout.activity_main_item, diffUtilItemCallback) {

    override fun View.bind(student: Student) {
        student.run {
            lblName.text = name
            lblAddress.text = address
            imgAvatar.loadUrl(photoUrl, R.drawable.ic_user, R.drawable.ic_user)
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
