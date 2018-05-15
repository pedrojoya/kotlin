package es.iessaladillo.pedrojoya.pr066.ui.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.pr066.R
import kotlinx.android.synthetic.main.fragment_main.*

private const val ARG_OPTION = "ARG_OPTION"

class MainFragment : Fragment() {

    private val option: String by lazy { arguments?.getString(ARG_OPTION)?:"" }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View?) {
        lblText.text = option
    }

    companion object {

        fun newInstance(option: String): MainFragment =
                MainFragment().apply {
                    arguments = bundleOf(ARG_OPTION to option)
                }

    }

}
