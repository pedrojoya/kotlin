package es.iessaladillo.pedrojoya.pr210.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.iessaladillo.pedrojoya.pr210.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailFragmentBaseViewModel
    private lateinit var viewModelClass: Class<DetailFragmentBaseViewModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val activity: DetailFragmentBaseActivity<*>
        try {
            // Activity must extend DetailFragmentBaseActivity.
            @Suppress("UNCHECKED_CAST")
            activity = (context as DetailFragmentBaseActivity<DetailFragmentBaseViewModel>)

            viewModelClass = activity.viewModelClass
        } catch (e: ClassCastException) {
            // La actividad no implementa la interfaz.
            throw ClassCastException(context?.javaClass?.name?:"Activity" + " debe heredar de DetailFragmentBaseActivity")
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(viewModelClass)
        viewModel.currentItem.observe(this, Observer<String> { showItem(it) })
    }

    private fun showItem(item: String) {
        lblItem.text = item
    }

    companion object {

        fun newInstance(): DetailFragment {
            return DetailFragment()
        }

    }

}