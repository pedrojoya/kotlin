package es.iessaladillo.pedrojoya.pr249.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr249.R
import es.iessaladillo.pedrojoya.pr249.extensions.extraString
import kotlinx.android.synthetic.main.fragment_detail.*

private const val ARG_ITEM = "ARG_ITEM"

class DetailFragment : Fragment() {

    private val item: String by extraString(ARG_ITEM)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        lblItem.text = item
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setIcon(R.drawable.ic_arrow_back_white_24dp)
            title = getString(R.string.detail_title)
        }
    }

    companion object {

        fun newInstance(item: String): DetailFragment =
                DetailFragment().apply {
                    arguments = bundleOf(ARG_ITEM to item)
                }

    }

}
