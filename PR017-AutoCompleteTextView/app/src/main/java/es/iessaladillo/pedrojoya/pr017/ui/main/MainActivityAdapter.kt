package es.iessaladillo.pedrojoya.pr017.ui.main

import android.view.View
import android.widget.Filter
import android.widget.Filterable
import es.iessaladillo.pedrojoya.pr017.R
import es.iessaladillo.pedrojoya.pr017.base.AdapterViewBaseAdapter
import es.iessaladillo.pedrojoya.pr017.data.local.model.Word
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main_item.*

class MainActivityAdapter(words: List<Word>) :
        AdapterViewBaseAdapter<Word, MainActivityAdapter.ViewHolder>(words, R.layout.activity_main_item),
        Filterable {

    private val original = words

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            data = original.filter { it.english.contains(constraint.toString(), true) }
            return Filter.FilterResults().apply {
                values = data
                count = data.size
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.count ?: 0 > 0) {
                notifyDataSetChanged()
            }
        }

    }

    class ViewHolder(override val containerView: View) : LayoutContainer {

        fun bind(word: Word) {
            imgPhoto.setImageResource(word.photoResId)
            lblEnglish.text = word.english
        }

    }

}
