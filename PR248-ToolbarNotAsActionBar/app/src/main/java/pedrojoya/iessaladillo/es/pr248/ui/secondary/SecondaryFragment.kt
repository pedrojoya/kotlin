package pedrojoya.iessaladillo.es.pr248.ui.secondary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.toolbar.*
import pedrojoya.iessaladillo.es.pr248.R
import pedrojoya.iessaladillo.es.pr248.extensions.toast

class SecondaryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_secondary, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.run {
            setTitle(R.string.secondary_title)
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
            inflateMenu(R.menu.fragment_secondary)
            setOnMenuItemClickListener {
                showSettings()
                true
            }
        }
    }

    private fun showSettings() {
        toast(getString(R.string.main_settings))
    }

    companion object {

        fun newInstance(): SecondaryFragment = SecondaryFragment()

    }

}
