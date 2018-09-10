package es.iessaladillo.pedrojoya.pr222.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr222.R
import es.iessaladillo.pedrojoya.pr222.extensions.inLandscape
import es.iessaladillo.pedrojoya.pr222.extensions.viewModelProvider
import es.iessaladillo.pedrojoya.pr222.ui.detail.DetailActivity
import es.iessaladillo.pedrojoya.pr222.ui.detail.DetailFragment
import es.iessaladillo.pedrojoya.pr222.ui.detail.TAG_DETAIL_FRAGMENT

class MainActivity : AppCompatActivity(), MainFragment.Callback, DetailFragment.Callback {

    private val viewModel: MainActivityViewModel by viewModelProvider { MainActivityViewModel(noItem) }
    private val noItem: String by lazy { getString(R.string.main_activity_no_item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Needed because MainActivity and MainActivityViewModel are destroyed
        // if we rotate being in DetailActivity.
        savedInstanceState?.let { viewModel.onRestoreInstanceState(it) }
        if (supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flMain, MainFragment.newInstance(), TAG_MAIN_FRAGMENT)
            }
        }
        if (inLandscape() && supportFragmentManager.findFragmentByTag(TAG_DETAIL_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flDetail, DetailFragment.newInstance(noItem), TAG_DETAIL_FRAGMENT)
            }
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
            supportFragmentManager.transaction {
                replace(R.id.flDetail, DetailFragment.newInstance(item), TAG_DETAIL_FRAGMENT)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                addToBackStack(item)
            }
        } else {
            DetailActivity.start(this, item)
        }
    }

    // When detail shown (even from backstack).
    override fun onDetailShown(item: String) {
        viewModel.selectedItem = item
        (supportFragmentManager.findFragmentById(R.id.flMain) as? MainFragment)?.selectItem(item)
    }

}