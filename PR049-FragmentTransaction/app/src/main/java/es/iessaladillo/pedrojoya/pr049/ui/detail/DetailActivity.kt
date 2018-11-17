package es.iessaladillo.pedrojoya.pr049.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr049.R
import es.iessaladillo.pedrojoya.pr049.extensions.extraString
import es.iessaladillo.pedrojoya.pr049.extensions.inLandscape

class DetailActivity : AppCompatActivity() {

    private val item: String by extraString(DetailFragment.EXTRA_ITEM, R.string.main_activity_no_item)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (inLandscape()) {
            // Not posible in landscape orientation.
            onBackPressed()
        } else {
            supportFragmentManager.commit {
                replace(R.id.flDetail, DetailFragment.newInstance(item), TAG_DETAIL_FRAGMENT )
            }
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
