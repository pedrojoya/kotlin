package es.iessaladillo.pedrojoya.pr100.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import es.iessaladillo.pedrojoya.pr100.R
import es.iessaladillo.pedrojoya.pr100.base.BaseListAdapter
import es.iessaladillo.pedrojoya.pr100.base.BaseViewHolder

class MainActivityAdapter(data: ArrayList<String>) : BaseListAdapter<String, MainActivityAdapter
.ViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout
                .activity_main_item, parent, false), this)
    }

    class ViewHolder(itemView: View,
                     adapter: BaseListAdapter<String, out BaseViewHolder<String>>) : BaseViewHolder<String>(itemView, adapter) {

        private val lblName: TextView = ViewCompat.requireViewById(itemView, R.id.lblName)

        override fun bind(item: String) {
            lblName.text = item
        }

    }

}
