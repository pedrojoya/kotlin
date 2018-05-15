package es.iessaladillo.pedrojoya.pr105.ui.main.option2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.data.local.Student
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_option2_tab1_item.*
import java.util.*

internal class Option2Tab1Adapter(private val data: ArrayList<Student>) :
        RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_option2_tab1_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

}

internal class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(student: Student) {
        with (student) {
            lblName.text = name
            lblAddress.text = address
            Picasso.with(imgAvatar.context)
                    .load(photoUrl)
                    .into(imgAvatar)
        }
    }

}

