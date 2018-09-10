package es.iessaladillo.pedrojoya.pr050.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr050.R
import es.iessaladillo.pedrojoya.pr050.ui.preferences.PreferencesActivity

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"
private const val TAG_INFO_FRAGMENT = "TAG_INFO_FRAGMENT"

class MainActivity : AppCompatActivity(), PhotoFragment.Callback, InfoFragment.Callback {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flContent, PhotoFragment.newInstance(), TAG_MAIN_FRAGMENT)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.mnuPreferences -> { showPreferences(); true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showPreferences() {
        PreferencesActivity.start(this)
    }

    override fun onInfoClicked() {
        if (supportFragmentManager.findFragmentByTag(TAG_INFO_FRAGMENT) ==  null) {
            supportFragmentManager.transaction {
                replace(R.id.flContent,
                        InfoFragment.newInstance(), TAG_INFO_FRAGMENT)
                addToBackStack(TAG_INFO_FRAGMENT)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            }
        } else {
            onBackPressed()
        }
    }

    override fun onPhotoClicked() {
        if (supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flContent,
                        PhotoFragment.newInstance(), TAG_MAIN_FRAGMENT)
                addToBackStack(TAG_MAIN_FRAGMENT)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            }
        } else {
            onBackPressed()
        }
    }

}
