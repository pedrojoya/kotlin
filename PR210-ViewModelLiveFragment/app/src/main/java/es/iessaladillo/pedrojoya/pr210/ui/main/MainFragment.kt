package es.iessaladillo.pedrojoya.pr210.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import es.iessaladillo.pedrojoya.pr210.R
import es.iessaladillo.pedrojoya.pr210.extensions.inLandScape
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
        initViews(view)
    }

    private fun initViews(view: View?) {
        val itemLayout = if (inLandScape())
            android.R.layout.simple_list_item_activated_1
        else
            android.R.layout.simple_list_item_1
        lstItems.adapter = ArrayAdapter(requireActivity(), itemLayout, viewModel.items)
        lstItems.setOnItemClickListener { _, _, position, _ -> markItem(position) }
    }

    private fun markItem(position: Int) {
        if (position >= 0) {
            lstItems.setItemChecked(position, true)
            lstItems.setSelection(position)
            viewModel.setCurrentItem(lstItems.getItemAtPosition(position) as String)
        } else {
            lstItems.clearChoices()
        }
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}