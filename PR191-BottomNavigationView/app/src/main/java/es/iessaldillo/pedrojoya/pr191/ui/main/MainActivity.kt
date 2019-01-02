package es.iessaldillo.pedrojoya.pr191.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import es.iessaldillo.pedrojoya.pr191.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels { MainActivityViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        observeCurrentOption()
        if (savedInstanceState == null) {
            navigateToStartFragment()
        }
    }

    private fun observeCurrentOption() {
        viewModel.currentOption.observe(this,
                Observer { option -> bottomNavigationView.menu.findItem(option)?.isChecked = true })
    }

    private fun navigateToStartFragment() {
        supportFragmentManager.commit {
            replace(R.id.flContent, MainFragment.newInstance(R.id.mnuFavorites, R.drawable.ic_favorite_black_24dp,
                    getString(R.string.main_favorites)))
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            navigateToOption(it)
            true
        }
    }

    private fun navigateToOption(item: MenuItem) {
        val currentOption = viewModel.currentOption.value
        if (currentOption == null || currentOption != item.itemId) {
            when (item.itemId) {
                R.id.mnuFavorites -> replaceFragment(MainFragment.newInstance(R.id.mnuFavorites, R.drawable.ic_favorite_black_24dp,
                        getString(R.string.main_favorites)), getString(R.string.main_favorites))
                R.id.mnuCalendar -> replaceFragment(MainFragment.newInstance(R.id.mnuCalendar, R.drawable.ic_access_time_black_24dp,
                        getString(R.string.main_calendar)), getString(R.string.main_calendar))
                R.id.mnuMusic -> replaceFragment(MainFragment.newInstance(R.id.mnuMusic, R.drawable.ic_audiotrack_black_24dp,
                        getString(R.string.main_music)), getString(R.string.main_music))
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment, tag)
                .addToBackStack(tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

}
