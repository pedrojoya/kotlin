package es.iessaladillo.pedrojoya.pr180.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr180.R
import es.iessaladillo.pedrojoya.pr180.base.Event
import es.iessaladillo.pedrojoya.pr180.base.RequestState
import es.iessaladillo.pedrojoya.pr180.data.remote.HttpClient
import es.iessaladillo.pedrojoya.pr180.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr180.utils.hideSoftKeyboard
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainActivityViewModel by lazy {
        getViewModel { MainActivityViewModel(HttpClient.getInstance(context!!)) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout
            .fragment_main, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        observeSearch()
        observeEcho()
    }

    private fun initViews() {
        btnSearch.setOnClickListener { _ -> search() }
        btnEcho.setOnClickListener { _ -> echo() }
    }

    private fun search() {
        val text = txtName.text.toString().trim()
        if (text.isBlank()) return
        hideSoftKeyboard()
        viewModel.search(text)
    }

    private fun echo() {
        val text = txtName.text.toString().trim()
        if (text.isBlank()) return
        hideSoftKeyboard()
        viewModel.requestEcho(text)
    }

    private fun observeSearch() {
        viewModel.searchLiveData.observe(this, Observer { searchRequest ->
            when (searchRequest) {
                is RequestState.Error -> showErrorSearching(searchRequest)
                is RequestState.Result<*> -> {
                    @Suppress("UNCHECKED_CAST")
                    val searchResult = searchRequest as RequestState.Result<Event<String>>
                    val result = searchResult.data.getContentIfNotHandled()
                    if (result != null) {
                        showResult(result)
                    }
                }
                is RequestState.Loading -> {
                    pbProgress!!.visibility = if (searchRequest.isLoading) View.VISIBLE else View.INVISIBLE
                }
            }
        })
    }

    private fun observeEcho() {
        viewModel.echoLiveData.observe(this, Observer { echoRequest ->
            when (echoRequest) {
                is RequestState.Error -> showErrorRequestingEcho(echoRequest)
                is RequestState.Result<*> -> {
                    val echoResult = echoRequest as RequestState.Result<Event<String>>
                    val result = echoResult.data.getContentIfNotHandled()
                    if (result != null) {
                        showResult(result)
                    }
                }
                is RequestState.Loading -> pbProgress!!.visibility = if (echoRequest.isLoading) View.VISIBLE else View.INVISIBLE
            }
        })
    }

    private fun showErrorSearching(searchError: RequestState.Error) {
        val exception = searchError.exception.getContentIfNotHandled()
        if (exception != null) {
            pbProgress.visibility = View.INVISIBLE
            Toast.makeText(requireContext(), exception.message ?: getString(R.string
                    .main_fragment_error_searching), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showErrorRequestingEcho(echoError: RequestState.Error) {
        val exception = echoError.exception.getContentIfNotHandled()
        if (exception != null) {
            pbProgress.visibility = View.INVISIBLE
            Toast.makeText(requireContext(), exception.message?:getString(R.string
                    .main_fragment_error_requesting_echo), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showResult(result: String) {
        pbProgress.visibility = View.INVISIBLE
        Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT).show()
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }

    }

}
