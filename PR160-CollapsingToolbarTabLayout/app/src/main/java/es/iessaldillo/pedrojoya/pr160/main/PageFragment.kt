package es.iessaldillo.pedrojoya.pr160.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import es.iessaldillo.pedrojoya.pr160.R
import kotlinx.android.synthetic.main.fragment_page.*

private const val ARG_PAGE_NUMBER = "ARG_PAGE_NUMBER"
private const val STATE_LIKES = "STATE_LIKES"

class PageFragment : Fragment() {

    private var fab: FloatingActionButton? = null
    private var likes: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_page, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        restoreInstanceState(savedInstanceState)
        view?.let { initViews() }
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        likes = savedInstanceState?.getInt(STATE_LIKES) ?: 0
    }

    private fun initViews() {
        fab = ActivityCompat.requireViewById(requireActivity(), R.id.fab)
        setupFab()
        lblLikes.text = likes.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_LIKES, likes)
    }

    // Hack to know if the fragment is currently visible in viewpager.
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            setupFab()
        }
    }

    private fun setupFab() {
        fab?.setImageResource(R.drawable.ic_thumb_up_white_24dp)
        fab?.setOnClickListener { like() }
    }


    private fun like() {
        likes++
        lblLikes.text = likes.toString()
    }

    companion object {

        fun newInstance(pageNumber: Int) =
                PageFragment().apply {
                    arguments = bundleOf(ARG_PAGE_NUMBER to pageNumber)
                }

    }

}
