package es.iessaladillo.pedrojoya.pr173

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_bottom_sheet.*

private const val STATE_BSB ="STATE_BSB"

class MainActivity : AppCompatActivity() {

    private lateinit var bsb: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)
        initViews(savedInstanceState)
    }

    private fun initViews(savedInstanceState: Bundle?) {
        setupBottomSheet(savedInstanceState?.getInt(STATE_BSB) ?: BottomSheetBehavior.STATE_COLLAPSED)
        imgDetail.setOnClickListener { expandOrCollapseBottomSheet() }
        fab.setOnClickListener { searchDailyPhoto() }
    }

    private fun setupBottomSheet(bsbState: Int) {
        bsb = BottomSheetBehavior.from(rlPanel).apply {
            setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    setupIcon(newState)
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
            state = bsbState
            setupIcon(state)
        }
    }

    fun setupIcon(newState: Int) {
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED ->
                imgDetail.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp)
            BottomSheetBehavior.STATE_COLLAPSED, BottomSheetBehavior.STATE_HIDDEN ->
                imgDetail.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp)
        }
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

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(STATE_BSB, bsb.state)
    }
}
