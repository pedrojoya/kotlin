package es.iessaladillo.pedrojoya.pr210.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.transaction

import es.iessaladillo.pedrojoya.pr210.R
import es.iessaladillo.pedrojoya.pr210.extensions.extraString
import es.iessaladillo.pedrojoya.pr210.extensions.inLandScape

private const val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"
private const val EXTRA_ITEM = "EXTRA_ITEM"

class DetailActivity : DetailFragmentBaseActivity<DetailActivityViewModel>() {

    override val viewModelClass: Class<DetailActivityViewModel>
        get() = DetailActivityViewModel::class.java

    private lateinit var viewModel: DetailActivityViewModel
    private val item by extraString(EXTRA_ITEM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        if (inLandScape()) {
            // Not posible in landscape orientation.
            onBackPressed()
        } else {
            viewModel.setCurrentItem(item)
            if (supportFragmentManager.findFragmentById(R.id.flDetail) == null) {
                supportFragmentManager.transaction {
                    replace(R.id.flDetail, DetailFragment.newInstance(), TAG_DETAIL_FRAGMENT)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun start(context: Context, item: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ITEM, item)
            context.startActivity(intent)
        }
    }

}