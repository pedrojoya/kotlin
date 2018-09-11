package es.iessaladillo.pedrojoya.pr147.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr147.R
import es.iessaladillo.pedrojoya.pr147.extensions.viewModelProvider
import kotlinx.android.synthetic.main.fragment_likes.*

class LikesFragment : Fragment() {

    // It can't be initialized lazily because activity is not ready yet when
    // the fab variable must be initialized (even lazily).
    private var fab: FloatingActionButton? = null
    private val viewModel by viewModelProvider<LikesFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_likes, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        fab = ActivityCompat.requireViewById(requireActivity(), R.id.fab)
        setupFab()
        lblLikes.text = viewModel.likes.toString()
    }

    private fun like() {
        viewModel.likes++
        lblLikes.text = viewModel.likes.toString()
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
