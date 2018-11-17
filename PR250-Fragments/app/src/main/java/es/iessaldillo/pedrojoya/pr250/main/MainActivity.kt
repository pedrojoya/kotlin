package es.iessaldillo.pedrojoya.pr250.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import es.iessaldillo.pedrojoya.pr250.R
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_FAVOURITES = "TAG_FAVOURITES"
private const val TAG_CALENDAR = "TAG_CALENDAR"
private const val TAG_MUSIC = "TAG_MUSIC"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        // Initially favourites option.
        if (savedInstanceState == null) {
            showFavorites()
        }
    }

    private fun setupViews() {
        btnFavourites.setOnClickListener { showFavorites() }
        btnCalendar.setOnClickListener { showCalendar() }
        btnMusic.setOnClickListener { showMusic() }
    }

    private fun showFavorites() {
        supportFragmentManager.commit {
            replace(R.id.flContent, MainFragment.newInstance(getString(R.string.main_favorites)), TAG_FAVOURITES)
        }
    }

    private fun showCalendar() {
        supportFragmentManager.commit {
            replace(R.id.flContent, MainFragment.newInstance(getString(R.string.main_calendar)), TAG_CALENDAR)
        }
    }

    private fun showMusic() {
        supportFragmentManager.commit {
            replace(R.id.flContent, MainFragment.newInstance(getString(R.string.main_music)), TAG_MUSIC)
        }
    }

}
