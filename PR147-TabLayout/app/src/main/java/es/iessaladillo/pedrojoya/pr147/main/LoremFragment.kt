package es.iessaladillo.pedrojoya.pr147.main


import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.iessaladillo.pedrojoya.pr147.R

class LoremFragment : Fragment() {

    private var fab: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lorem, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.let { initViews() }
    }

    private fun initViews() {
        fab = ActivityCompat.requireViewById(requireActivity(), R.id.fab)
        setupFab()
    }

    private fun share() {
        Toast.makeText(requireContext(), getString(R.string.lorem_fragment_share), Toast.LENGTH_SHORT).show()
    }

    // Hack to know if the fragment is currently visible in viewpager.
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            setupFab()
        }
    }

    private fun setupFab() {
        fab?.setImageResource(R.drawable.ic_share_white_24dp)
        fab?.setOnClickListener { share() }
    }

    companion object {

        fun newInstance(): LoremFragment {
            return LoremFragment()
        }

    }

}
