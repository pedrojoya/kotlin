package es.iessaladillo.pedrojoya.pr222.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr222.R
import es.iessaladillo.pedrojoya.pr222.extensions.inLandscape
import es.iessaladillo.pedrojoya.pr222.extensions.replaceFragment

class DetailActivity : AppCompatActivity(), DetailFragment.Callback {

    private val item: String by lazy {
        intent?.getStringExtra(DetailFragment.EXTRA_ITEM) ?: getString(R.string
                .main_activity_no_item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (inLandscape()) {
            // Not posible in landscape orientation.
            onBackPressed()
        } else {
            replaceFragment(R.id.flDetail, DetailFragment.newInstance(item), TAG_DETAIL_FRAGMENT)
        }
    }

    override fun onDetailShown(item: String) {
        // Do nothing.
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun start(context: Context, item: String) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(DetailFragment.EXTRA_ITEM, item)
            })
        }

    }

}