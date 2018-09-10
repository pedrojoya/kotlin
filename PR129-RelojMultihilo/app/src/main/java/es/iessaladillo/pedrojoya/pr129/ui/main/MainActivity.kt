package es.iessaladillo.pedrojoya.pr129.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr129.R
import es.iessaladillo.pedrojoya.pr129.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        viewModel.clockLiveData.observe(this, Observer<String> { updateTime(it) })
    }

    private fun initViews() {
        lblTime.text = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        btnStart.setText(if (viewModel.isClockRunning()) R.string.activity_main_btnStop else R
                .string.activity_main_btnStart)
        btnStart.setOnClickListener { _ ->
            if (btnStart.text.toString() == getString(R.string.activity_main_btnStart)) {
                start()
            } else {
                stop()
            }
        }
    }

    private fun start() {
        viewModel.start()
        btnStart.setText(R.string.activity_main_btnStop)
    }

    private fun stop() {
        viewModel.stop()
        btnStart.setText(R.string.activity_main_btnStart)
    }

    private fun updateTime(time: String) {
        lblTime.text = time
    }

}
