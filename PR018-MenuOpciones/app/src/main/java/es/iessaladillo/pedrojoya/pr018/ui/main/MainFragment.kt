package es.iessaladillo.pedrojoya.pr018.ui.main


import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr018.R
import es.iessaladillo.pedrojoya.pr018.extensions.toast
import es.iessaladillo.pedrojoya.pr018.extensions.viewModelProvider
import es.iessaladillo.pedrojoya.pr018.utils.BINARY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.GREY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.INVERTED_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.SEPIA_COLOR_FILTER
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModelProvider {
        MainFragmentViewModel(R.id.mnuOriginal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        imgPhoto.visibility = if (viewModel.isVisible) View.VISIBLE else View.INVISIBLE
        setCorrectBitmap(viewModel.effectId)
    }

    private fun setCorrectBitmap(@IdRes itemId: Int) {
        when (itemId) {
            R.id.mnuOriginal -> imgPhoto.drawable.colorFilter = null
            R.id.mnuGrey -> imgPhoto.drawable.colorFilter = GREY_COLOR_FILTER
            R.id.mnuSepia -> imgPhoto.drawable.colorFilter = SEPIA_COLOR_FILTER
            R.id.mnuBinary -> imgPhoto.drawable.colorFilter = BINARY_COLOR_FILTER
            R.id.mnuInverted -> imgPhoto.drawable.colorFilter = INVERTED_COLOR_FILTER
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_main, menu)
        menu.findItem(viewModel.effectId)?.isChecked = true
        menu.findItem(R.id.mnuVisible)?.isChecked = viewModel.isVisible
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        if (menu.findItem(R.id.mnuInverted)?.isChecked == true) {
            menu.findItem(R.id.mnuActions).isEnabled = false
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuInfo -> {
                requireContext().toast(R.string.main_info)
                return true
            }
            R.id.mnuVisible -> {
                viewModel.toggleVisibility()
                item.isChecked = viewModel.isVisible
                imgPhoto.visibility = if (viewModel.isVisible) View.VISIBLE else View.INVISIBLE
                return true
            }
            R.id.mnuEdit -> {
                requireContext().toast(R.string.main_edit)
                return true
            }
            R.id.mnuDelete -> {
                requireContext().toast(R.string.main_delete)
                return true
            }
            R.id.mnuOriginal, R.id.mnuGrey, R.id.mnuSepia, R.id.mnuBinary, R.id.mnuInverted -> {
                viewModel.effectId = item.itemId
                item.isChecked = true
                setCorrectBitmap(item.itemId)
                requireActivity().invalidateOptionsMenu()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }

    }

}
