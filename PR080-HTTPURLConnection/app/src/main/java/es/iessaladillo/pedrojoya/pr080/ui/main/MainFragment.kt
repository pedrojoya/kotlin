package es.iessaladillo.pedrojoya.pr080.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.iessaladillo.pedrojoya.pr080.R
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.RequestState
import es.iessaladillo.pedrojoya.pr080.extensions.hideKeyboard
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initViews()
        observeSearch()
        observeEcho()
    }

    private fun initViews() {
        btnSearch.setOnClickListener { _ -> search() }
        btnEcho.setOnClickListener { _ -> echo() }
    }

    private fun observeSearch() {
        viewModel.searchLiveData.observe(viewLifecycleOwner, Observer<RequestState> {
            when (it) {
                is RequestState.Error -> showErrorSearching(it)
                is RequestState.Result<*> -> {
                    @Suppress("UNCHECKED_CAST")
                    (it as RequestState.Result<Event<String>>).data.getContentIfNotHandled()?.let { result ->
                        showResult(result)
                    }
                }
                is RequestState.Loading -> pbProgress.visibility = if (it.isLoading) View.VISIBLE else View.INVISIBLE
            }
        })
    }

    private fun observeEcho() {
        viewModel.echoLiveData.observe(viewLifecycleOwner, Observer<RequestState> {
            @Suppress("UNCHECKED_CAST")
            when (it) {
                is RequestState.Error -> showErrorRequestingEcho(it)
                is RequestState.Result<*> -> (it as RequestState.Result<Event<String>>).data.getContentIfNotHandled()?.let { result ->
                    showResult(result) }
                is RequestState.Loading -> pbProgress.visibility = if (it.isLoading) View.VISIBLE else View.INVISIBLE
            }
        })
    }

    private fun search() {
        val text = txtName.text.toString()
        if (text.isBlank()) return
        requireActivity().hideKeyboard()
        viewModel.search(text)
    }

    private fun echo() {
        val text = txtName.text.toString()
        if (text.isBlank()) return
        requireActivity().hideKeyboard()
        viewModel.requestEcho(text)
    }

    private fun showErrorSearching(searchError: RequestState.Error) {
        searchError.exception.getContentIfNotHandled()?.run {
            pbProgress.visibility = View.INVISIBLE
            Toast.makeText(requireActivity(), message,
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun showErrorRequestingEcho(echoError: RequestState.Error) {
        echoError.exception.getContentIfNotHandled()?.run {
            pbProgress.visibility = View.INVISIBLE
            Toast.makeText(requireActivity(), message,
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun showResult(result: String) {
        pbProgress.visibility = View.INVISIBLE
        Toast.makeText(requireActivity(),
                if (result.isNotBlank()) result else getString(R.string.main_fragment_not_found),
                Toast.LENGTH_SHORT).show()
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
