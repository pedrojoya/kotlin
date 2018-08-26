package es.iessaldillo.pedrojoya.pr191.ui.main

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import es.iessaldillo.pedrojoya.pr191.R
import es.iessaldillo.pedrojoya.pr191.extensions.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_FAVORITES = "TAG_FAVORITES"
private const val TAG_CALENDAR = "TAG_CALENDAR"
private const val TAG_MUSIC = "TAG_MUSIC"

private const val STATE_CURRENT_ITEM_ID = "STATE_CURRENT_ITEM_ID"

class MainActivity : AppCompatActivity() {

    @IdRes private var currentItemId: Int = R.id.mnuCalendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreInstanceState(savedInstanceState)
        initViews()
        // We simulate click on first option.
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = currentItemId
            // bottomNavigationView.findViewById(currentItemId).performClick();
        }
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        currentItemId = savedInstanceState?.getInt(STATE_CURRENT_ITEM_ID, R.id.mnuCalendar)?:R.id.mnuCalendar
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(STATE_CURRENT_ITEM_ID, currentItemId)
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.menu.findItem(R.id.mnuMusic).isEnabled = currentItemId != R.id.mnuFavorites
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mnuFavorites -> showFavorites()
                R.id.mnuCalendar -> showCalendar()
                R.id.mnuMusic -> showMusic()
            }
            currentItemId = item.itemId
            true
        }
    }

    private fun showFavorites() {
        replaceFragment(R.id.flContent, MainFragment.newInstance(getString(R.string.main_activity_favorites)), TAG_FAVORITES)
        // Disable music option.
        bottomNavigationView.menu.findItem(R.id.mnuMusic).isEnabled = false
    }

    private fun showCalendar() {
        replaceFragment(R.id.flContent, MainFragment.newInstance(getString(R.string.main_activity_calendar)), TAG_CALENDAR)
        // Enable music option.
        bottomNavigationView.menu.findItem(R.id.mnuMusic).isEnabled = true
    }

    private fun showMusic() {
        replaceFragment(R.id.flContent, MainFragment.newInstance(getString(R.string.main_activity_music)), TAG_MUSIC)
    }

}
