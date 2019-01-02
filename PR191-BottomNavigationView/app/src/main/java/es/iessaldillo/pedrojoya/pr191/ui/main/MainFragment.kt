package es.iessaldillo.pedrojoya.pr191.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaldillo.pedrojoya.pr191.R
import es.iessaldillo.pedrojoya.pr191.extensions.extraInt
import es.iessaldillo.pedrojoya.pr191.extensions.extraString
import es.iessaldillo.pedrojoya.pr191.extensions.snackbar
import kotlinx.android.synthetic.main.fragment_main.*

private const val ARG_OPTION_TITLE = "ARG_OPTION_TITLE"
private const val ARG_OPTION_MENU_RES_ID = "ARG_OPTION_MENU_RES_ID"
private const val ARG_OPTION_ICON_RES_ID = "ARG_OPTION_ICON_RES_ID"

class MainFragment : Fragment() {

    private val optionTitle: String by extraString(ARG_OPTION_TITLE)
    private val optionMenuResId: Int by extraInt(ARG_OPTION_MENU_RES_ID)
    private val optionIconResId: Int by extraInt(ARG_OPTION_ICON_RES_ID)
    private val activityViewModel: MainActivityViewModel by activityViewModels { MainActivityViewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        activityViewModel.setOption(optionMenuResId)
    }


    private fun setupViews() {
        lblOption.text = optionTitle
        setupFab()
    }

    private fun setupFab() {
        ActivityCompat.requireViewById<FloatingActionButton>(requireActivity(), R.id.fab).run {
            setOnClickListener {
                it.snackbar(R.string.main_fab_clicked)
            }
            setImageResource(optionIconResId)
        }
    }

    companion object {

        fun newInstance(@IdRes optionMenuResId: Int, @DrawableRes optionIconResId: Int, optionTitle: String): MainFragment =
                MainFragment().apply {
                    arguments = bundleOf(
                            ARG_OPTION_TITLE to optionTitle,
                            ARG_OPTION_MENU_RES_ID to optionMenuResId,
                            ARG_OPTION_ICON_RES_ID to optionIconResId)
                }

    }

}
