package es.iessaladillo.pedrojoya.pr222.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr222.R
import kotlinx.android.synthetic.main.fragment_detail.*

const val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"

class DetailFragment : Fragment() {

    private val item: String by lazy {
        arguments?.getString(EXTRA_ITEM) ?: getString(R.string.main_activity_no_item)
    }
    private var listener: Callback? = null

    interface Callback {
        fun onDetailShown(item: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        try {
            listener = activity as Callback?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement fragment callback")
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showItem()
    }

    private fun showItem() {
        lblItem.text = item
        // Notify activity (needed in case of landscape configuration).
        listener?.onDetailShown(item)
    }

    companion object {

        const val EXTRA_ITEM = "EXTRA_ITEM"

        fun newInstance(item: String): DetailFragment =
                DetailFragment().apply {
                    arguments = bundleOf(EXTRA_ITEM to item)
                }

    }

}