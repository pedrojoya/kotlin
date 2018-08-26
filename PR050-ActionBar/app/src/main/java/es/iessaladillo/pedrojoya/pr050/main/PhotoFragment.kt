package es.iessaladillo.pedrojoya.pr050.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr050.R
import es.iessaladillo.pedrojoya.pr050.utils.BINARY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.GREY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.INVERTED_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr050.utils.SEPIA_COLOR_FILTER
import kotlinx.android.synthetic.main.fragment_photo.*

private const val STATE_EFFECT = "STATE_EFFECT"

class PhotoFragment : Fragment() {

    private var effectId: Int = R.id.mnuOriginal
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

    override fun onAttach(activity: Context?) {
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
        restoreInstanceState(savedInstanceState)
        initViews()
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let { effectId = savedInstanceState.getInt(STATE_EFFECT) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_EFFECT, effectId)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_photo, menu)
        menu?.findItem(effectId)?.isChecked = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?) =
        when (item!!.itemId) {
            R.id.mnuInfo -> {
                listener?.onInfoClicked(); true
            }
            R.id.mnuOriginal, R.id.mnuGrey, R.id.mnuSepia, R.id.mnuBinary, R.id.mnuInverted -> {
                effectId = item.itemId
                item.isChecked = true
                setCorrectBitmap(item.itemId)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun initViews() {
        setCorrectBitmap(effectId)
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
