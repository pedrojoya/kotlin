package es.iessaladillo.pedrojoya.pr080.ui.main


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr080.R
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.data.RepositoryImpl
import es.iessaladillo.pedrojoya.pr080.data.echo.EchoDataSourceImpl
import es.iessaladillo.pedrojoya.pr080.data.search.SearchDataSourceImpl
import es.iessaladillo.pedrojoya.pr080.extensions.hideSoftKeyboard
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(RepositoryImpl(SearchDataSourceImpl(), EchoDataSourceImpl()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeSearchResult()
        observeEchoResult()
    }

    private fun setupViews() {
        btnSearch.setOnClickListener { search() }
        btnEcho.setOnClickListener { echo() }
    }

    private fun search() {
        val text = txtName.text.toString()
        if (TextUtils.isEmpty(text.trim { it <= ' ' })) return
        requireActivity().hideSoftKeyboard()
        viewModel.search(text)
    }

    private fun echo() {
        val text = txtName.text.toString()
        if (TextUtils.isEmpty(text.trim { it <= ' ' })) return
        requireActivity().hideSoftKeyboard()
        viewModel.requestEcho(text)
    }

    private fun observeSearchResult() {
        viewModel.searchResultLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when {
                resource.isLoading -> pbProgress.visibility = View.VISIBLE
                resource.hasError() -> showErrorSearching(resource.exception!!)
                resource.hasSuccess() -> showResult(resource.data!!)
            }
        })
    }

    private fun observeEchoResult() {
        viewModel.echoResultLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when {
                resource.isLoading -> pbProgress.visibility = View.VISIBLE
                resource.hasError() -> showErrorRequestingEcho(resource.exception!!)
                resource.hasSuccess() -> showResult(resource.data!!)
            }
        })
    }

    private fun showErrorSearching(exceptionEvent: Event<Exception>) {
        val exception = exceptionEvent.getContentIfNotHandled()
        if (exception != null) {
            pbProgress.visibility = View.INVISIBLE
            var message: String? = exception.message
            if (message == null) message = getString(R.string.main_error_searching)
            Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showErrorRequestingEcho(exceptionEvent: Event<Exception>) {
        val exception = exceptionEvent.getContentIfNotHandled()
        if (exception != null) {
            pbProgress.visibility = View.INVISIBLE
            var message: String? = exception.message
            if (message == null) message = getString(R.string.main_error_requesting_echo)
            Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showResult(result: Event<String>) {
        val message = result.getContentIfNotHandled()
        if (message != null) {
            pbProgress.visibility = View.INVISIBLE
            Toast.makeText(requireContext(),
                    if (!TextUtils.isEmpty(message)) message else getString(R.string.main_no_results),
                    Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
