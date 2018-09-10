package es.iessaladillo.pedrojoya.pr050.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.pr050.R

class InfoFragment : Fragment() {

    private var mListener: Callback? = null

    interface Callback {
        fun onPhotoClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_info, container, false)

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        try {
            mListener = activity as Callback?
        } catch (e: ClassCastException) {
            throw ClassCastException(
                    activity.toString() + " must implement InfoFragment.Callback")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.fragment_info, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?) =
        when (item!!.itemId) {
            R.id.mnuPhoto -> { mListener?.onPhotoClicked(); true }
            else -> super.onOptionsItemSelected(item)
        }

    companion object {

        fun newInstance(): InfoFragment = InfoFragment()

    }

}
