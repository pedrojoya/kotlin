package es.iessaladillo.pedrojoya.pr105.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.extensions.snackbar
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        collapsingToolbar.title = title
        fab.setOnClickListener { showMessage() }
    }

    private fun showMessage() {
        fab.snackbar(getString(R.string.detail_activity_fab_clicked))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, DetailActivity::class.java))
        }

    }

}
