package es.iessaladillo.pedrojoya.pr147.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr147.R
import es.iessaladillo.pedrojoya.pr147.extensions.toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            loadInitialFragment()
        }
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
        toast(getString(R.string.main_settings))
    }

}
