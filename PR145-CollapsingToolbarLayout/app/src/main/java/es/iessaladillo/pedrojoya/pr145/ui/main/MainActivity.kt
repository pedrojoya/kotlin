package es.iessaladillo.pedrojoya.pr145.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr145.R
import kotlinx.android.synthetic.main.activity_main.*


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
        collapsingToolbar.title = getString(R.string.main_title)
    }

    private fun loadInitialFragment() {
        supportFragmentManager.commit {
            replace(R.id.flContent, MainFragment.newInstance(), MainFragment::class.java.simpleName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSettings) {
            showSettings()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSettings() {
        Toast.makeText(this, getString(R.string.main_mnuSettings), Toast.LENGTH_SHORT).show()
    }

}
