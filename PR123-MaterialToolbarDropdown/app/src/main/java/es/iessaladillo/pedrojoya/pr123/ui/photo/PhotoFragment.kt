package es.iessaladillo.pedrojoya.pr123.ui.photo

import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr123.R
import es.iessaladillo.pedrojoya.pr123.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.pr123.ui.main.MainActivityViewModelFactory
import es.iessaladillo.pedrojoya.pr123.utils.BINARY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr123.utils.GREY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr123.utils.INVERTED_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr123.utils.SEPIA_COLOR_FILTER
import kotlinx.android.synthetic.main.fragment_photo.*


class PhotoFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels { MainActivityViewModelFactory(R.id.mnuOriginal) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_photo, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeEffect()
    }

    private fun observeEffect() {
        viewModel.effectResId.observe(viewLifecycleOwner, Observer<Int> { this.setCorrectBitmap(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_photo, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.mnuFilter -> {
                showPopupMenu(requireActivity().findViewById(R.id.mnuFilter))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun showPopupMenu(v: View) {
        // Context NOT from toolbar.
        with (PopupMenu(imgPhoto.context, v)) {
            inflate(R.menu.fragment_photo_popup)
            setOnMenuItemClickListener { onPopupMenuItemClick(it) }
            val currentEffectResId = viewModel.effectResId.value
            if (currentEffectResId != null) {
                menu.findItem(currentEffectResId)?.isChecked = true
            }
            show()
        }
    }

    private fun onPopupMenuItemClick(item: MenuItem): Boolean {
        viewModel.setEffectResId(item.itemId)
        item.isChecked = true
        viewModel.setEffectResId(item.itemId)
        return true
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

    companion object {

        fun newInstance(): PhotoFragment = PhotoFragment()

    }

}
