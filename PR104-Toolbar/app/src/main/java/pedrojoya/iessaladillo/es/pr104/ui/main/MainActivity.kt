package pedrojoya.iessaladillo.es.pr104.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main.*
import pedrojoya.iessaladillo.es.pr104.R
import pedrojoya.iessaladillo.es.pr104.extensions.toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        if (savedInstanceState == null) {
            loadInitialFragment()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
    }

    private fun loadInitialFragment() {
        supportFragmentManager.commit {
            replace(R.id.flContent, MainFragment.newInstance(), MainFragment::class.java.simpleName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            R.id.mnuSettings -> {
                showSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun showSettings() {
        toast(getString(R.string.main_mnuSettings))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
