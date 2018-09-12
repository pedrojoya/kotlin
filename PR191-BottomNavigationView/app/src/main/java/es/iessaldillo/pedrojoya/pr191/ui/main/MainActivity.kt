package es.iessaldillo.pedrojoya.pr191.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaldillo.pedrojoya.pr191.R
import es.iessaldillo.pedrojoya.pr191.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_FAVORITES = "TAG_FAVORITES"
private const val TAG_CALENDAR = "TAG_CALENDAR"
private const val TAG_MUSIC = "TAG_MUSIC"

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        // We simulate click on first option.
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = viewModel.currentItemId
            // bottomNavigationView.findViewById(currentItemId).performClick();
        }
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.run {
            menu.findItem(R.id.mnuMusic).isEnabled =
                    viewModel.currentItemId != R.id.mnuFavorites
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.mnuFavorites -> showFavorites()
                    R.id.mnuCalendar -> showCalendar()
                    R.id.mnuMusic -> showMusic()
                }
                viewModel.currentItemId = item.itemId
                true
            }
        }
    }

    private fun showFavorites() {
        supportFragmentManager.transaction {
            replace(R.id.flContent, MainFragment.newInstance(getString(R.string.main_activity_favorites)), TAG_FAVORITES)
        }
        // Disable music option.
        bottomNavigationView.menu.findItem(R.id.mnuMusic).isEnabled = false
    }

    private fun showCalendar() {
        supportFragmentManager.transaction {
            replace(R.id.flContent, MainFragment.newInstance(getString(R.string.main_activity_calendar)), TAG_CALENDAR)
        }
        // Enable music option.
        bottomNavigationView.menu.findItem(R.id.mnuMusic).isEnabled = true
    }

    private fun showMusic() {
        supportFragmentManager.transaction {
            replace(R.id.flContent, MainFragment.newInstance(getString(R.string.main_activity_music)), TAG_MUSIC)
        }
    }

}
