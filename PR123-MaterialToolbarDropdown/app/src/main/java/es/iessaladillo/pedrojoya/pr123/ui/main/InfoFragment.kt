package es.iessaladillo.pedrojoya.pr123.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr123.R
import kotlinx.android.synthetic.main.fragment_info.*

private const val EXTRA_LIKES = "EXTRA_LIKES"

class InfoFragment : Fragment() {

    private var likes = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        likes = savedInstanceState?.getInt(EXTRA_LIKES, 0) ?: 0
        initViews()
    }

    private fun initViews() {
        lblLikes.text = resources.getQuantityString(R.plurals.info_fragment_likes, likes, likes)
        imgLike.setOnClickListener { incrementLikes() }
    }

    private fun incrementLikes() {
        likes++
        lblLikes.text = resources.getQuantityString(R.plurals.info_fragment_likes, likes, likes)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_LIKES, likes)
    }

    companion object {

        fun newInstance(): InfoFragment {
            return InfoFragment()
        }

    }

}
