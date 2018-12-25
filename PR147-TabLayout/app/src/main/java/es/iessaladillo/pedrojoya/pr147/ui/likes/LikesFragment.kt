package es.iessaladillo.pedrojoya.pr147.ui.likes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr147.R
import kotlinx.android.synthetic.main.fragment_likes.*

class LikesFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private val viewModel: LikesFragmentViewModel by viewModels { LikesFragmentViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_likes, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fab = ActivityCompat.requireViewById(requireActivity(), R.id.fab)
        setupFab()
        observeLikes()
    }

    private fun observeLikes() {
        viewModel.likes.observe(viewLifecycleOwner, Observer {
            likes ->  lblLikes.text = likes.toString()
        })
    }

    // Hack to know if the fragment is currently visible in viewpager.
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isVisible) {
            setupFab()
        }
    }

    private fun setupFab() {
        fab.setOnClickListener { viewModel.incrementLikes() }
    }

    companion object {

        fun newInstance(): LikesFragment = LikesFragment()

    }

}
