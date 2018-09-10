package pedrojoya.iessaladillo.es.pr225.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*
import pedrojoya.iessaladillo.es.pr225.R
import pedrojoya.iessaladillo.es.pr225.data.local.model.Student
import pedrojoya.iessaladillo.es.pr225.extensions.inflate
import pedrojoya.iessaladillo.es.pr225.extensions.loadUrl

class MainActivityAdapter(private val data: List<Student> = emptyList()) :
        RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.activity_main_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(student: Student) {
            student.run {
                lblName.text = name
                lblAddress.text = address
                imgAvatar.loadUrl(photoUrl, R.drawable.ic_person_black_24dp, R.drawable.ic_person_black_24dp)
            }
        }

    }

}

