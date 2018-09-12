package es.iessaladillo.pedrojoya.pr210.ui.main

import android.os.Bundle
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr210.R
import es.iessaladillo.pedrojoya.pr210.data.Database
import es.iessaladillo.pedrojoya.pr210.extensions.inLandScape
import es.iessaladillo.pedrojoya.pr210.extensions.viewModelProvider
import es.iessaladillo.pedrojoya.pr210.ui.detail.DetailActivity
import es.iessaladillo.pedrojoya.pr210.ui.detail.DetailFragment
import es.iessaladillo.pedrojoya.pr210.ui.detail.DetailFragmentBaseActivity

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"
private const val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"

class MainActivity : DetailFragmentBaseActivity<MainActivityViewModel>() {

    private val viewModel: MainActivityViewModel by viewModelProvider {
        MainActivityViewModel(Database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // This is the correct order to load the fragments.
        if (supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flMain, MainFragment.newInstance(), TAG_MAIN_FRAGMENT)
            }
        }
        if (inLandScape() && supportFragmentManager.findFragmentByTag(TAG_DETAIL_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flDetail, DetailFragment.newInstance(), TAG_MAIN_FRAGMENT)
            }
        }
        viewModel.currentItem.observe(this, Observer{ item ->
            if (inLandScape()) {
                DetailActivity.start(this, item)
            }
        })
    }

    // We need to take care of onSaveInstanceState because the activity is finished if we change
    // orientation while activity is in the backstack, so the have to save the selected
    // item.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.onRestoreInstanceState(savedInstanceState)
    }

    override fun getViewModelClass(): Class<MainActivityViewModel> {
        return MainActivityViewModel::class.java
    }

}