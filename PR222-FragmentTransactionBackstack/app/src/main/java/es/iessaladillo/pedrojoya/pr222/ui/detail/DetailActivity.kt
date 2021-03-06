package es.iessaladillo.pedrojoya.pr222.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr222.R
import es.iessaladillo.pedrojoya.pr222.extensions.extraString
import es.iessaladillo.pedrojoya.pr222.extensions.inLandscape

class DetailActivity : AppCompatActivity(), DetailFragment.Callback {

    private val item: String by extraString(DetailFragment.EXTRA_ITEM, R.string.main_activity_no_item)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (inLandscape()) {
            // Not posible in landscape orientation.
            onBackPressed()
        } else {
            supportFragmentManager.transaction {
                replace(R.id.flDetail, DetailFragment.newInstance(item), TAG_DETAIL_FRAGMENT)
            }
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