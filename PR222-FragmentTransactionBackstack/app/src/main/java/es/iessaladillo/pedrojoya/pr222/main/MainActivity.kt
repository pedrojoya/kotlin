package es.iessaladillo.pedrojoya.pr222.main

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr222.R
import es.iessaladillo.pedrojoya.pr222.detail.DetailActivity
import es.iessaladillo.pedrojoya.pr222.detail.DetailFragment
import es.iessaladillo.pedrojoya.pr222.detail.TAG_DETAIL_FRAGMENT
import es.iessaladillo.pedrojoya.pr222.extensions.*

class MainActivity : AppCompatActivity(), MainFragment.Callback, DetailFragment.Callback {

    private lateinit var viewModel: MainActivityViewModel
    private val noItem: String by lazy { getString(R.string.main_activity_no_item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel { MainActivityViewModel(noItem) }
        // Needed because MainActivity and MainActivityViewModel are destroyed
        // if we rotate being in DetailActivity.
        savedInstanceState?.let { viewModel.onRestoreInstanceState(it) }
        if (findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            replaceFragment(R.id.flMain,
                    MainFragment.newInstance(), TAG_MAIN_FRAGMENT)
        }
        if (inLandscape() && findFragmentByTag(TAG_DETAIL_FRAGMENT) == null) {
            replaceFragment(R.id.flDetail,
                    DetailFragment.newInstance(noItem), TAG_DETAIL_FRAGMENT)
        }
        if (!inLandscape() && viewModel.selectedItem != noItem) {
            DetailActivity.start(this, viewModel.selectedItem)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.onSaveInstanceState(outState)
    }

    override fun onItemSelected(item: String) {
        viewModel.selectedItem = item
        if (inLandscape()) {
            replaceFragment(R.id.flDetail,
                    DetailFragment.newInstance(item), TAG_DETAIL_FRAGMENT, true, item,
                    FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        } else {
            DetailActivity.start(this, item)
        }
    }

    // When detail shown (even from backstack).
    override fun onDetailShown(item: String) {
        viewModel.selectedItem = item
        (findFragmentById(R.id.flMain) as? MainFragment)?.selectItem(item)
    }

}