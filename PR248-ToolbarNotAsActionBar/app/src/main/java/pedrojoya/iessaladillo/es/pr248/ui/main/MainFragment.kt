package pedrojoya.iessaladillo.es.pr248.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.toolbar.*
import pedrojoya.iessaladillo.es.pr248.R
import pedrojoya.iessaladillo.es.pr248.extensions.toast
import pedrojoya.iessaladillo.es.pr248.ui.secondary.SecondaryFragment

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.run {
            setTitle(R.string.app_name)
            inflateMenu(R.menu.fragment_main)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.mnuNext -> {
                        navigateToSecondary()
                        true
                    }
                    R.id.mnuSettings -> {
                        navigateToSettings()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun navigateToSecondary() {
        requireFragmentManager().commit {
            replace(R.id.flContent, SecondaryFragment.newInstance(), SecondaryFragment::class.java.simpleName)
            addToBackStack(SecondaryFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_NONE)
        }
    }

    private fun navigateToSettings() {
        toast(getString(R.string.main_settings))
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
