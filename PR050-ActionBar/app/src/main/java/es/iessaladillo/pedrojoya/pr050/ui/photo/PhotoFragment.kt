package es.iessaladillo.pedrojoya.pr050.ui.photo

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr050.R
import es.iessaladillo.pedrojoya.pr050.extensions.viewModelProvider
import es.iessaladillo.pedrojoya.pr050.utils.BINARY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.GREY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.INVERTED_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.SEPIA_COLOR_FILTER
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : Fragment() {

    private val viewModel: PhotoFragmentViewModel by viewModelProvider()
    private var listener: Callback? = null

    interface Callback {
        fun onInfoClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_photo, container, false)

    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        try {
            listener = activity as Callback?
        } catch (e: ClassCastException) {
            throw ClassCastException(
                    activity.toString() + " must implement PhotoFragment.Callback")
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupActionBar()
        // Initial state.
        setCorrectBitmap(viewModel.effectId)
    }

    private fun setupActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.photo_title)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_photo, menu)
        menu.findItem(viewModel.effectId)?.isChecked = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.mnuInfo -> {
                listener?.onInfoClicked(); true
            }
            R.id.mnuOriginal, R.id.mnuGrey, R.id.mnuSepia, R.id.mnuBinary, R.id.mnuInverted -> {
                viewModel.effectId = item.itemId
                item.isChecked = true
                setCorrectBitmap(item.itemId)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun setCorrectBitmap(@IdRes itemId: Int) {
        imgPhoto.drawable.colorFilter = when (itemId) {
            R.id.mnuGrey -> GREY_COLOR_FILTER
            R.id.mnuSepia -> SEPIA_COLOR_FILTER
            R.id.mnuBinary -> BINARY_COLOR_FILTER
            R.id.mnuInverted -> INVERTED_COLOR_FILTER
            else -> null
        }
    }

    companion object {

        fun newInstance(): PhotoFragment {
            return PhotoFragment()
        }

    }

}
