package es.iessaladillo.pedrojoya.pr059.ui.main

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr059.R
import kotlinx.android.extensions.LayoutContainer

class MainFragmentAdapter : ListAdapter<String, MainFragmentAdapter.ViewHolder>(object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return TextUtils.equals(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return TextUtils.equals(oldItem, newItem)
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    public override fun getItem(position: Int): String {
        return super.getItem(position)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var text1: TextView = ViewCompat.requireViewById(containerView, android.R.id.text1)

        init {
            containerView.setOnClickListener {
                if (adapterPosition != adapterPosition) {
                    showStudent(getItem(adapterPosition))
                }
            }
        }

        fun bind(student: String) {
            text1.text = student
        }

        private fun showStudent(student: String) {
            Toast.makeText(itemView.context,
                    itemView.context.getString(R.string.main_student_clicked, student),
                    Toast.LENGTH_SHORT).show()
        }

    }

}
