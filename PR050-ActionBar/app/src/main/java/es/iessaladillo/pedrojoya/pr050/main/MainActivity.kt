package es.iessaladillo.pedrojoya.pr050.main

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import es.iessaladillo.pedrojoya.pr050.R
import es.iessaladillo.pedrojoya.pr050.extensions.findFragmentByTag
import es.iessaladillo.pedrojoya.pr050.extensions.replaceFragment
import es.iessaladillo.pedrojoya.pr050.preferences.PreferencesActivity

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"
private const val TAG_INFO_FRAGMENT = "TAG_INFO_FRAGMENT"

class MainActivity : AppCompatActivity(), PhotoFragment.Callback, InfoFragment.Callback {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            replaceFragment(R.id.flContent, PhotoFragment.newInstance(), TAG_MAIN_FRAGMENT)
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
        if (findFragmentByTag(TAG_INFO_FRAGMENT) ==  null) {
            replaceFragment(R.id.flContent,
                InfoFragment.newInstance(), TAG_INFO_FRAGMENT, true, TAG_INFO_FRAGMENT,
                FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        } else {
            onBackPressed()
        }
    }

    override fun onPhotoClicked() {
        if (findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            replaceFragment(R.id.flContent,
                    PhotoFragment.newInstance(), TAG_MAIN_FRAGMENT, true, TAG_MAIN_FRAGMENT,
                    FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        } else {
            onBackPressed()
        }
    }

}
