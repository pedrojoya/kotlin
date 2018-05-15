package es.iessaladillo.pedrojoya.pr049.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr049.R
import es.iessaladillo.pedrojoya.pr049.extensions.inLandscape
import es.iessaladillo.pedrojoya.pr049.extensions.replaceFragment

class DetailActivity : AppCompatActivity() {

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
            replaceFragment(R.id.flDetail,
                    DetailFragment.newInstance(item), TAG_DETAIL_FRAGMENT )
        }
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