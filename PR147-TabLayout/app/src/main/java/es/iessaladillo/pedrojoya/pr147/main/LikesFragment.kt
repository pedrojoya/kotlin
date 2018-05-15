package es.iessaladillo.pedrojoya.pr147.main


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.iessaladillo.pedrojoya.pr147.R
import kotlinx.android.synthetic.main.fragment_likes.*

private const val STATE_LIKES = "STATE_LIKES"

class LikesFragment : Fragment() {

    private var fab: FloatingActionButton? = null
    private var likes: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_likes, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        restoreIntanceState(savedInstanceState)
        view?.let { initViews() }
    }

    private fun restoreIntanceState(savedInstanceState: Bundle?) {
        likes = savedInstanceState?.getInt(STATE_LIKES) ?: 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_LIKES, likes)
    }

    private fun initViews() {
        fab = ActivityCompat.requireViewById(requireActivity(), R.id.fab)
        setupFab()
        lblLikes.text = likes.toString()
    }

    private fun like() {
        likes++
        lblLikes.text = likes.toString()
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

    companion object {

        fun newInstance(): LikesFragment {
            return LikesFragment()
        }

    }

}
