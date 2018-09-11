package es.iessaladillo.pedrojoya.pr066.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.transaction
import com.google.android.material.navigation.NavigationView
import es.iessaladillo.pedrojoya.pr066.R
import es.iessaladillo.pedrojoya.pr066.extensions.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_appbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        // if just lunched, select default menu item in drawer.
        if (savedInstanceState == null) {
            onNavigationItemSelected(navigationView.menu.getItem(0))
        }

    }

    private fun initViews() {
        setupToolbar()
        setupNavigationDrawer()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupNavigationDrawer() {
        ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close).apply {
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
                supportFragmentManager.transaction {
                    replace(R.id.flContent, MainFragment.newInstance(item.title.toString()))
                }
                item.isChecked = true
            }
            R.id.mnuOption5 -> showOption5Activity()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showOption5Activity() {
        toast(R.string.main_activity_show_option5)
    }

}
