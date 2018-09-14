package es.iessaladillo.pedrojoya.pr049.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr049.R
import es.iessaladillo.pedrojoya.pr049.extensions.extraString
import kotlinx.android.synthetic.main.fragment_detail.*

const val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"

class DetailFragment : Fragment() {

    private val item: String by extraString(EXTRA_ITEM, R.string.main_activity_no_item)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showItem(item)
    }

    fun showItem(item: String) {
        lblItem.text = item
    }

    companion object {

        const val EXTRA_ITEM = "EXTRA_ITEM"

        fun newInstance(item: String): DetailFragment =
                DetailFragment().apply {
                    arguments = bundleOf(EXTRA_ITEM to item)
                }

    }

}