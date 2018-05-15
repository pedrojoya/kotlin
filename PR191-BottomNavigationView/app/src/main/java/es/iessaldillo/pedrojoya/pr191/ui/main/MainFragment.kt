package es.iessaldillo.pedrojoya.pr191.ui.main


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import es.iessaldillo.pedrojoya.pr191.R
import kotlinx.android.synthetic.main.fragment_main.*

private const val ARG_OPTION = "ARG_OPTION"

class MainFragment : Fragment() {

    private val option: String by lazy {
        arguments?.getString(ARG_OPTION) ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View?) {
        lblOption.text = option
        setupFab()
    }

    private fun setupFab() {
        with(ActivityCompat.requireViewById<FloatingActionButton>(requireActivity(), R.id.fab)) {
            setOnClickListener {
                Snackbar.make(it, "Me han pulsado", Snackbar.LENGTH_SHORT).show()
            }
            setImageResource(when (option) {
                getString(R.string.main_activity_calendar) -> R.drawable.ic_access_time_black_24dp
                getString(R.string.main_activity_favorites) -> R.drawable.ic_favorite_black_24dp
                else -> R.drawable.ic_audiotrack_black_24dp
            })
        }
    }

    companion object {

        fun newInstance(option: String): MainFragment =
                MainFragment().apply {
                    arguments = bundleOf(ARG_OPTION to option)
                }

    }

}
