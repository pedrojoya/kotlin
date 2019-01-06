package es.iessaladillo.pedrojoya.pr237.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr237.R
import es.iessaladillo.pedrojoya.pr237.base.Event
import es.iessaladillo.pedrojoya.pr237.base.Resource
import kotlinx.android.synthetic.main.fragment_main.*

private const val MAX_STEPS = 10

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeWorkingTask()
    }

    private fun setupViews() {
        btnStart.setOnClickListener { viewModel.startWorking(MAX_STEPS) }
        btnCancel.setOnClickListener { viewModel.cancelWorking() }
    }

    private fun observeWorkingTask() {
        viewModel.workingTask.observe(viewLifecycleOwner, Observer { resource ->
            updateViews(resource)
            if (resource.hasError()) {
                showError(resource.exception!!)
            } else if (resource.hasSuccess()) {
                showSuccess(resource.data!!)
            }
        })
    }

    private fun updateViews(resource: Resource<Event<String>>) {
        lblMessage.text = if (resource.isLoading)
            getString(R.string.main_lblMessage,
                    resource.progress, MAX_STEPS)
        else
            ""
        btnStart.isEnabled = !resource.isLoading
        btnCancel.isEnabled = resource.isLoading
        prbBar.visibility = if (resource.isLoading) View.VISIBLE else View.INVISIBLE
        if (resource.isLoading) {
            prbBar.progress = resource.progress ?: 0
        }
        lblMessage.visibility = if (resource.isLoading) View.VISIBLE else View.INVISIBLE
        prbCircle.visibility = if (resource.isLoading) View.VISIBLE else View.INVISIBLE
    }

    private fun showError(exceptionEvent: Event<Exception>) {
        exceptionEvent.getContentIfNotHandled()?.let {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSuccess(messageEvent: Event<String>) {
        messageEvent.getContentIfNotHandled()?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
