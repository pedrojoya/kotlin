package es.iessaladillo.pedrojoya.pr148.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr148.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupFab()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        // TODO: We disable de title because we can't make it collapse properly
        collapsingToolbarLayout?.run {
            isTitleEnabled = false
            title = getString(R.string.detail_title)
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupFab() {
        fab.setOnClickListener { save() }
    }

    private fun save() {
        Toast.makeText(requireContext(), getString(R.string.detail_fab_clicked), Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        requireActivity().window.statusBarColor = ResourcesCompat.getColor(resources,
                android.R.color.transparent, requireActivity().theme)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().window.statusBarColor = ResourcesCompat.getColor(resources,
                R.color.colorPrimaryDark, requireActivity().theme)
    }

    companion object {

        fun newInstance(): DetailFragment = DetailFragment()

    }

}
