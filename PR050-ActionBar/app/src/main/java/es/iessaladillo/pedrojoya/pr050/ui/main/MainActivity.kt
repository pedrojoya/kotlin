package es.iessaladillo.pedrojoya.pr050.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr050.R
import es.iessaladillo.pedrojoya.pr050.ui.info.InfoFragment
import es.iessaladillo.pedrojoya.pr050.ui.photo.PhotoFragment
import es.iessaladillo.pedrojoya.pr050.ui.preferences.PreferencesActivity

class MainActivity : AppCompatActivity(), PhotoFragment.Callback {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        if (supportFragmentManager.findFragmentByTag(PhotoFragment::class.java.simpleName) == null) {
            supportFragmentManager.commit {
                replace(R.id.flContent, PhotoFragment.newInstance(), PhotoFragment::class.java.simpleName)
            }
        }
    }

    private fun setupActionBar() {
        supportActionBar?.run {
            setIcon(R.drawable.ic_arrow_back_white_24dp)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.mnuPreferences -> {
            showPreferences(); true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showPreferences() {
        PreferencesActivity.start(this)
    }

    override fun onInfoClicked() {
        supportFragmentManager.commit {
            replace(R.id.flContent,
                    InfoFragment.newInstance(), InfoFragment::class.java.simpleName)
            addToBackStack(InfoFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
