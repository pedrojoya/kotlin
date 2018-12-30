package pedrojoya.iessaladillo.es.pr104.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import pedrojoya.iessaladillo.es.pr104.R
import pedrojoya.iessaladillo.es.pr104.ui.secondary.SecondaryFragment

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.main_title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.mnuNext -> {
                navigateToSecondary()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun navigateToSecondary() {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.flContent, SecondaryFragment.newInstance(), SecondaryFragment::class.java
                    .simpleName)
            addToBackStack(SecondaryFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }
    }

    companion object {

        fun newInstance() = MainFragment()

    }

}