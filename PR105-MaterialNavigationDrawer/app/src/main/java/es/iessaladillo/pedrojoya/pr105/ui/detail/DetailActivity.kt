package es.iessaladillo.pedrojoya.pr105.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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
        supportActionBar?.run {
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
