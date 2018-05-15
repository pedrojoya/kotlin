package es.iessaladillo.pedrojoya.pr049.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr049.R
import es.iessaladillo.pedrojoya.pr049.detail.DetailActivity
import es.iessaladillo.pedrojoya.pr049.detail.DetailFragment
import es.iessaladillo.pedrojoya.pr049.detail.TAG_DETAIL_FRAGMENT
import es.iessaladillo.pedrojoya.pr049.extensions.findFragmentByTag
import es.iessaladillo.pedrojoya.pr049.extensions.getViewModel
import es.iessaladillo.pedrojoya.pr049.extensions.inLandscape
import es.iessaladillo.pedrojoya.pr049.extensions.replaceFragment

class MainActivity : AppCompatActivity(), MainFragment.Callback {

    private lateinit var viewModel: MainActivityViewModel
    private var frgDetailFragment: DetailFragment? = null
    private val noItem by lazy { getString(R.string.main_activity_no_item) }

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
        if (inLandscape()) {
            frgDetailFragment = DetailFragment.newInstance(viewModel.selectedItem)
            replaceFragment(R.id.flDetail,
                    frgDetailFragment!!, TAG_DETAIL_FRAGMENT)
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
            frgDetailFragment?.showItem(item)
        } else {
            DetailActivity.start(this, item)
        }
    }

}