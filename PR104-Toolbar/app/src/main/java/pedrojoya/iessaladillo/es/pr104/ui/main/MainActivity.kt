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
            navigateToStartFragment()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun navigateToStartFragment() {
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
                navigateToSettings()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun navigateToSettings() {
        toast(getString(R.string.main_mnuSettings))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
