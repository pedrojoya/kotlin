package es.iessaladillo.pedrojoya.pr146.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr146.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        // TODO: We disable de title because we can't make it collapse properly
        collapsingToolbarLayout.run {
            isTitleEnabled = false
            title = getString(R.string.detail_title)
        }
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            setDisplayHomeAsUpEnabled(true)
        }
        requireActivity().window.statusBarColor = ResourcesCompat.getColor(resources,
                android.R.color.transparent, requireActivity().theme)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.statusBarColor = ResourcesCompat.getColor(resources,
                R.color.colorPrimaryDark, requireActivity().theme)
    }

    companion object {

        fun newInstance(): DetailFragment = DetailFragment()

    }

}
