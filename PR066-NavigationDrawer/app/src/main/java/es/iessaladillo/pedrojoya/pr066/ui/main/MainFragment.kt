package es.iessaladillo.pedrojoya.pr066.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr066.R
import es.iessaladillo.pedrojoya.pr066.extensions.extraString
import kotlinx.android.synthetic.main.fragment_main.*

private const val ARG_OPTION = "ARG_OPTION"

class MainFragment : Fragment() {

    private val option: String by extraString(ARG_OPTION)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        lblText.text = option
    }

    companion object {

        fun newInstance(option: String): MainFragment =
                MainFragment().apply {
                    arguments = bundleOf(ARG_OPTION to option)
                }

    }

}
