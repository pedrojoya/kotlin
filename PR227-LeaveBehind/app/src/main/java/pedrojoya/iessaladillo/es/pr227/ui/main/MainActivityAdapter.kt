package pedrojoya.iessaladillo.es.pr227.ui.main

import android.view.View
import kotlinx.android.synthetic.main.activity_main_item.view.*
import pedrojoya.iessaladillo.es.pr227.R
import pedrojoya.iessaladillo.es.pr227.base.BaseAdapter
import pedrojoya.iessaladillo.es.pr227.data.local.model.Student
import pedrojoya.iessaladillo.es.pr227.extensions.loadUrl

class MainActivityAdapter(data: ArrayList<Student> = ArrayList()) : BaseAdapter<Student>(data, R.layout.activity_main_item) {

    override fun View.bind(item: Student) {
        item.run {
            lblName.text = name
            lblAddress.text = address
            imgAvatar.loadUrl(photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
        }
    }

}



