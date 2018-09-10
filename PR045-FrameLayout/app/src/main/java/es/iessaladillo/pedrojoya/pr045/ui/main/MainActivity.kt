package es.iessaladillo.pedrojoya.pr045.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr045.R
import es.iessaladillo.pedrojoya.pr045.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModelProvider<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        imgDetail.setOnClickListener { toggleDetailVisibility() }
        setupPanelState(viewModel.isDetailOpen)
    }

    private fun setupPanelState(isVisible: Boolean) {
        if (isVisible) {
            lblDetail.visibility = View.VISIBLE
            imgDetail.setImageResource(R.drawable.expand_anim)
        } else {
            lblDetail.visibility = View.GONE
            imgDetail.setImageResource(R.drawable.collapse_anim)
        }
    }

    private fun toggleDetailVisibility() {
        viewModel.isDetailOpen = !viewModel.isDetailOpen
        setupPanelState(viewModel.isDetailOpen)
    }

}
