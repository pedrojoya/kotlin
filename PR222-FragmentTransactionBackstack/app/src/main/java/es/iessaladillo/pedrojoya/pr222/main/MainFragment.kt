package es.iessaladillo.pedrojoya.pr222.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import es.iessaladillo.pedrojoya.pr222.R
import es.iessaladillo.pedrojoya.pr222.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr222.extensions.inLandscape
import es.iessaladillo.pedrojoya.pr222.extensions.inflate
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
            container?.inflate(R.layout.fragment_main)

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
        lstItems.adapter = ArrayAdapter(requireContext(), itemLayout, viewModel.items)
        if (inLandscape()) {
            selectItem(viewModel.selectedItem)
        }
        lstItems.setOnItemClickListener { _, _, position, _ ->
            showItem(position)
        }
    }

    private fun showItem(position: Int) {
        selectItem(position)
        listener?.onItemSelected(lstItems.getItemAtPosition(position) as String)
    }


    fun selectItem(selectedItem: String) {
        viewModel.selectedItem = selectedItem
        selectItem(viewModel.items.indexOf(selectedItem))
    }

    private fun selectItem(position: Int) {
        with(lstItems) {
            if (position >= 0) {
                setItemChecked(position, true)
                setSelection(position)
            } else {
                setItemChecked(-1, true)
                clearChoices()
            }
        }
    }

//    // Needed in case activity is destroy because of low memory.
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        viewModel.onSaveInstanceState(outState)
//    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }

    }

}