package es.iessaladillo.pedrojoya.pr249.ui.list

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr249.R
import es.iessaladillo.pedrojoya.pr249.ui.detail.DetailFragment
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.support_simple_spinner_dropdown_item.*

class ListFragmentAdapter : ListAdapter<String,
        ListFragmentAdapter.ViewHolder>(object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return TextUtils.equals(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return TextUtils.equals(oldItem, newItem)
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListFragmentAdapter.ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false))

    override fun onBindViewHolder(viewHolder: ListFragmentAdapter.ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder
    (containerView), LayoutContainer {

        init {
            containerView.setOnClickListener { navigateToDetail(getItem(adapterPosition)) }
        }

        fun bind(item: String) {
            text1.text = item
        }

        private fun navigateToDetail(item: String) {
            (itemView.context as AppCompatActivity).supportFragmentManager.commit {
                replace(R.id.flContent, DetailFragment.newInstance(item), DetailFragment::class.java.simpleName)
                addToBackStack(DetailFragment::class.java.simpleName)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }
        }


    }

}
