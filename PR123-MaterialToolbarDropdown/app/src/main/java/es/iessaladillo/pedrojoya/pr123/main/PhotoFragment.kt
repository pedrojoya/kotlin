package es.iessaladillo.pedrojoya.pr123.main

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.PopupMenu
import android.view.*
import es.iessaladillo.pedrojoya.pr123.R
import es.iessaladillo.pedrojoya.pr123.utils.BINARY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr123.utils.GREY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr123.utils.INVERTED_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr123.utils.SEPIA_COLOR_FILTER
import kotlinx.android.synthetic.main.fragment_photo.*

private const val STATE_EFFECT = "STATE_EFFECT"

class PhotoFragment : Fragment() {

    private var effectId = R.id.mnuOriginal

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        restoreInstanceState(savedInstanceState)
        initViews()
        super.onActivityCreated(savedInstanceState)
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        effectId = savedInstanceState?.getInt(STATE_EFFECT, R.id.mnuOriginal)?:R.id.mnuOriginal
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_EFFECT, effectId)
    }

    private fun initViews() {
        setCorrectBitmap(effectId)
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
            menu.findItem(effectId).isChecked = true
            show()
        }
    }

    private fun onPopupMenuItemClick(item: MenuItem): Boolean {
        effectId = item.itemId
        item.isChecked = true
        setCorrectBitmap(item.itemId)
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

        fun newInstance(): PhotoFragment {
            return PhotoFragment()
        }

    }

}
