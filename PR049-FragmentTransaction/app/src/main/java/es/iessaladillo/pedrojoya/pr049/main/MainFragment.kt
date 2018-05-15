package es.iessaladillo.pedrojoya.pr049.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import es.iessaladillo.pedrojoya.pr049.R
import es.iessaladillo.pedrojoya.pr049.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr049.extensions.inLandscape
import kotlinx.android.synthetic.main.fragment_main.*

const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainFragment : Fragment() {

    private var listener: Callback? = null

    private lateinit var viewModel: MainActivityViewModel

    // Comunication interface with activity.
    interface Callback {
        fun onItemSelected(item: String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onAttach(activity: Context?) {
        super.onAttach(activity)
        try {
            listener = activity as Callback?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement fragment callback")
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = requireActivity().getViewModel()
        initViews()
    }

    private fun initViews() {
        val itemLayout = if (requireContext().inLandscape())
            android.R.layout.simple_list_item_activated_1
        else
            android.R.layout.simple_list_item_1
        val data = viewModel.items
        lstItems.adapter = ArrayAdapter(requireContext(), itemLayout, data)
        if (inLandscape()) {
            val selectedIndex = data.indexOf(viewModel.selectedItem)
            if (selectedIndex >= 0) {
                selectItem(selectedIndex)
            }
        }
        lstItems.setOnItemClickListener { _, _, position, _ ->
            showItem(position)
        }
    }

    private fun showItem(position: Int) {
        selectItem(position)
        listener?.onItemSelected(lstItems.getItemAtPosition(position) as String)
    }

    private fun selectItem(position: Int) {
        with(lstItems) {
            if (position >= 0) {
                setItemChecked(position, true)
                setSelection(position)
            } else {
                clearChoices()
            }
        }
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }

    }

}