package es.iessaladillo.pedrojoya.pr123.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr123.R
import es.iessaladillo.pedrojoya.pr123.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.pr123.ui.main.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels {
        MainActivityViewModelFactory(R.id.mnuOriginal)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeLikes()
    }

    private fun setupViews() {
        imgLike.setOnClickListener { viewModel.incrementLikes() }
    }

    private fun observeLikes() {
        viewModel.likes.observe(viewLifecycleOwner, Observer { this.showLikes(it) })
    }

    private fun showLikes(likes: Int) {
        lblLikes.text = resources.getQuantityString(R.plurals.info_fragment_likes, likes, likes)
    }

    companion object {

        fun newInstance(): InfoFragment = InfoFragment()

    }

}
