package es.iessaladillo.pedrojoya.pr015.ui.main

import android.view.View
import es.iessaladillo.pedrojoya.pr015.R
import es.iessaladillo.pedrojoya.pr015.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr015.data.local.model.Word
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

class MainActivityAdapter(data: List<Word>) :
        AdapterViewBaseAdapter<Word, MainActivityAdapter.ViewHolder>(data, R.layout.activity_main_item) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(override val containerView: View) : LayoutContainer {

        fun bind(word: Word) {
            word.run {
                imgPhoto.setImageResource(photoResId)
                lblEnglish.text = english
                lblSpanish.text = spanish
            }
        }

    }

}
