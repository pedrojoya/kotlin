package es.iessaladillo.pedrojoya.pr105.ui.main.option3


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.pr105.extensions.snackbar
import kotlinx.android.synthetic.main.fragment_option3.*


class Option3Fragment : Fragment() {

    private lateinit var listener: OnToolbarAvailableListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_option3, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        setupCollapsingToolbar()
        setupFab()
    }

    private fun setupFab() {
        fab.setOnClickListener { showMessage() }
    }

    private fun showMessage() {
        fab.snackbar(R.string.option3_fragment_fab_clicked)
    }


    private fun setupCollapsingToolbar() {
        collapsingToolbar.title = getString(R.string.activity_main_option3)
    }

    private fun setupToolbar() {
        listener.onToolbarAvailable(toolbar, getString(R.string.activity_main_option3))
    }

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        try {
            listener = activity as OnToolbarAvailableListener
        } catch (e: Exception) {
            throw ClassCastException(activity!!.toString() + " must implement OnToolbarAvailableListener")
        }

    }

}
