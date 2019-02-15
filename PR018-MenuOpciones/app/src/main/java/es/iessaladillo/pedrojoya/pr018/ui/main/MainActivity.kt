package es.iessaladillo.pedrojoya.pr018.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr018.R
import es.iessaladillo.pedrojoya.pr018.extensions.toast

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Load initial fragment.
        if (savedInstanceState == null) {
            loadInitialFragment()
        }
    }

    private fun loadInitialFragment() {
        supportFragmentManager.commit {
            replace(R.id.flContent, MainFragment.newInstance(), MainFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSettings) {
            toast(R.string.main_settings)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}