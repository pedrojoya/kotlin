package es.iessaladillo.pedrojoya.pr045

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

private const val STATE_DETAIL_VISIBLE = "STATE_DETAIL_VISIBLE"

class MainActivity : AppCompatActivity() {

    private var detailVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)
        restoreInstance(savedInstanceState)
        initViews()
    }

    private fun restoreInstance(savedInstanceState: Bundle?) {
        detailVisible = savedInstanceState?.getBoolean(STATE_DETAIL_VISIBLE,
                false) ?: false
    }

    private fun initViews() {
        imgDetail.setOnClickListener { toggleDetailVisibility() }
        setupPanelState(detailVisible)
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
        detailVisible = !detailVisible
        setupPanelState(detailVisible)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_DETAIL_VISIBLE, detailVisible)
    }

}
