package es.iessaladillo.pedrojoya.pr105.ui.main.option1


import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.fragment_option1.*


class Option1Fragment : Fragment() {

    private lateinit var listener: OnToolbarAvailableListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_option1, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        setupFab()
    }

    private fun setupFab() {
        fab.setOnClickListener { showMessage() }
    }

    private fun showMessage() {
        Snackbar.make(fab, R.string.option1_fragment_fab_clicked, Snackbar.LENGTH_SHORT)
                .show()
    }

    private fun setupToolbar() {
        listener.onToolbarAvailable(toolbar, getString(R.string.activity_main_option1))
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
