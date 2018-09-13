package es.iessaladillo.pedrojoya.pr237.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr237.R
import es.iessaladillo.pedrojoya.pr237.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

private const val MAX_STEPS = 10

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        viewModel.task.observe(this, Observer<Int> { this.updateViews(it) })
    }

    private fun initViews() {
        btnStart.setOnClickListener { viewModel.startWorking(MAX_STEPS) }
        btnCancel.setOnClickListener { cancel() }
        updateViews(0)
    }

    private fun cancel() {
        viewModel.cancelWorking()
    }

    private fun updateViews(step: Int) {
        val working = step in 1..(MAX_STEPS - 1) && viewModel.isWorking
        if (!working) {
            lblMessage.text = ""
        }
        prbBar.progress = step
        lblMessage.text = getString(R.string.activity_main_lblMessage, step, MAX_STEPS)
        btnStart.isEnabled = !working
        btnCancel.isEnabled = working
        prbBar.visibility = if (working) View.VISIBLE else View.INVISIBLE
        lblMessage.visibility = if (working) View.VISIBLE else View.INVISIBLE
        prbCircle.visibility = if (working) View.VISIBLE else View.INVISIBLE
    }

}
