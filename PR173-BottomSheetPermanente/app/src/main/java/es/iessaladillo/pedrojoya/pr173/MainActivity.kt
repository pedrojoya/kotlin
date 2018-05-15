package es.iessaladillo.pedrojoya.pr173

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_bottom_sheet.*

class MainActivity : AppCompatActivity() {

    private lateinit var bsb: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        setupBottomSheet()
        imgDetail.setOnClickListener { expandOrCollapseBottomSheet() }
        fab.setOnClickListener { searchDailyPhoto() }
    }

    private fun setupBottomSheet() {
        bsb = BottomSheetBehavior.from(rlPanel)
        bsb.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED ->
                        imgDetail.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp)
                    BottomSheetBehavior.STATE_COLLAPSED, BottomSheetBehavior.STATE_HIDDEN ->
                        imgDetail.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun searchDailyPhoto() {
        startActivity(Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, getString(R.string.main_activity_daily_photo))
        })
    }

    private fun expandOrCollapseBottomSheet() {
        when (bsb.state) {
            BottomSheetBehavior.STATE_EXPANDED -> bsb.state = BottomSheetBehavior.STATE_COLLAPSED
            BottomSheetBehavior.STATE_COLLAPSED -> bsb.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

}
