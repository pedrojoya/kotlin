package es.iessaladillo.pedrojoya.pr147.ui.lorem


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr147.R

class LoremFragment : Fragment() {

    private lateinit var fab: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_lorem, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Only first fragment in ViewPager should do this.
        setupFab()
    }

    private fun setupFab() {
        fab = ActivityCompat.requireViewById(requireActivity(), R.id.fab)
        fab.setOnClickListener { share() }
    }

    private fun share() {
        Snackbar.make(fab, getString(R.string.lorem_share), Snackbar.LENGTH_SHORT).show()
    }

    // Hack to know if the fragment is currently visible in viewpager.
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isVisible) {
            setupFab()
        }
    }

    companion object {

        fun newInstance(): LoremFragment = LoremFragment()

    }

}
