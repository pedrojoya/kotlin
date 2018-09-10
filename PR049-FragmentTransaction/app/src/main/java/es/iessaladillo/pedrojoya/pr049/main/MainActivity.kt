package es.iessaladillo.pedrojoya.pr049.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr049.R
import es.iessaladillo.pedrojoya.pr049.detail.DetailActivity
import es.iessaladillo.pedrojoya.pr049.detail.DetailFragment
import es.iessaladillo.pedrojoya.pr049.detail.TAG_DETAIL_FRAGMENT
import es.iessaladillo.pedrojoya.pr049.extensions.inLandscape
import es.iessaladillo.pedrojoya.pr049.extensions.viewModelProvider

class MainActivity : AppCompatActivity(), MainFragment.Callback {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(noItem)
    }
    private val noItem by lazy { getString(R.string.main_activity_no_item) }

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
        if (inLandscape()) {
            supportFragmentManager.transaction {
                replace(R.id.flDetail, DetailFragment.newInstance(viewModel.selectedItem), TAG_DETAIL_FRAGMENT)
            }
        } else if (viewModel.selectedItem != noItem) {
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
            (supportFragmentManager.findFragmentByTag(TAG_DETAIL_FRAGMENT) as? DetailFragment)?.showItem(item)
        } else {
            DetailActivity.start(this, item)
        }
    }

}