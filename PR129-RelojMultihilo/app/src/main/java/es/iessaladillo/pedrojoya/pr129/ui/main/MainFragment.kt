package es.iessaladillo.pedrojoya.pr129.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr129.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        observeRunning()
        observeClock()
    }

    private fun setupViews() {
        btnStart.setOnClickListener { viewModel.startOrStop() }
    }

    private fun observeRunning() {
        viewModel.getRunning().observe(viewLifecycleOwner, Observer { running ->
            btnStart.setText(if (running) R.string.main_btnStop else R.string.main_btnStart) })
    }

    private fun observeClock() {
        viewModel.clock.observe(viewLifecycleOwner, Observer { time -> lblTime.text = time })
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
