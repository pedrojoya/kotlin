package es.iessaladillo.pedrojoya.pr066.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import es.iessaladillo.pedrojoya.pr066.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        // if just lunched, select default menu item in drawer.
        if (savedInstanceState == null) {
            onNavigationItemSelected(navigationView.menu.getItem(0))
        }

    }

    private fun setupViews() {
        setupToolbar()
        setupNavigationDrawer()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupNavigationDrawer() {
        ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.main_open_navigation_drawer,
                R.string.main_close_navigation_drawer).apply {
            drawerLayout.addDrawerListener(this)
            syncState()
        }
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuOption1, R.id.mnuOption2, R.id.mnuOption3, R.id.mnuOption4 -> {
                navigateToOption(item.title.toString())
                item.isChecked = true
            }
            R.id.mnuOption5 -> navigateToOption5Activity()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun navigateToOption5Activity() {
        Toast.makeText(this, getString(R.string.main_navigateToOption5), Toast.LENGTH_SHORT).show()
    }

    private fun navigateToOption(title: String) {
        supportFragmentManager.commit {
            replace(R.id.flContent, MainFragment.newInstance(title))
        }
    }

}
