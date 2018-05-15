package es.iessaladillo.pedrojoya.pr105.ui.detail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr105.R
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        with (supportActionBar!!) {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        collapsingToolbar.title = title
        fab.setOnClickListener { showMessage() }
    }

    private fun showMessage() {
        Snackbar.make(nestedScrollView!!, R.string.detail_activity_fab_clicked, Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
