package es.iessaladillo.pedrojoya.pr173.ui.main

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import es.iessaladillo.pedrojoya.pr173.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main_bottom_sheet.*

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels {
        MainFragmentViewModelFactory(BottomSheetBehavior.STATE_COLLAPSED)
    }
    private val bsb: BottomSheetBehavior<View> by lazy { BottomSheetBehavior.from(rlPanel) }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, parent, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupBottomSheet()
        imgDetail.setOnClickListener { expandOrCollapseBottomSheet() }
        fab.setOnClickListener { searchDailyPhoto() }
    }

    private fun setupBottomSheet() {
        bsb.run {
            setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    viewModel.bsbState = newState
                    updateIcon(newState)
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
            state = viewModel.bsbState
            updateIcon(state)
        }
    }

    private fun updateIcon(newState: Int) {
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED -> imgDetail!!.setImageResource(R.drawable.ic_arrow_drop_down_white_24dp)
            BottomSheetBehavior.STATE_COLLAPSED, BottomSheetBehavior.STATE_HIDDEN -> imgDetail!!.setImageResource(R.drawable.ic_arrow_drop_up_white_24dp)
        }
    }

    private fun searchDailyPhoto() {
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, getString(R.string.main_daily_photo))
        try {
            startActivity(intent)
        } catch (exception: ActivityNotFoundException) {
            Toast.makeText(requireContext(), getString(R.string.main_no_activity_available), Toast.LENGTH_SHORT).show()
        }

    }

    private fun expandOrCollapseBottomSheet() {
        if (bsb.state == BottomSheetBehavior.STATE_EXPANDED) {
            bsb.setState(BottomSheetBehavior.STATE_COLLAPSED)
        } else if (bsb.state == BottomSheetBehavior.STATE_COLLAPSED) {
            bsb.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
