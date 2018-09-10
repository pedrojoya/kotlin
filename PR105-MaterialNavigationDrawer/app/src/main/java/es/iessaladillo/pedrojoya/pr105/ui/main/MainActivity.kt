package es.iessaladillo.pedrojoya.pr105.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.transaction
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.pr105.ui.detail.DetailActivity
import es.iessaladillo.pedrojoya.pr105.ui.main.option1.Option1Fragment
import es.iessaladillo.pedrojoya.pr105.ui.main.option2.Option2Fragment
import es.iessaladillo.pedrojoya.pr105.ui.main.option3.Option3Fragment
import kotlinx.android.synthetic.main.activity_main.*

private const val PREFERENCES_FILE = "prefs"
private const val PREF_NAV_DRAWER_OPENED = "navdrawerOpened"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnToolbarAvailableListener {

    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null

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
        setupNavigationDrawer()
    }

    private fun setupNavigationDrawer() {
        //val navigationView: NavigationView = ActivityCompat.requireViewById(this, R.id
        //        .navigationView)
        val imgProfile: ImageView = ViewCompat.requireViewById(navigationView.getHeaderView(0), R.id.imgProfile)
        Picasso.with(this).load("http://lorempixel.com/200/200/people/").into(imgProfile)
        val swDownloadedOnly = navigationView.menu.findItem(
                R.id.mnuDownloaded).actionView as SwitchCompat
        swDownloadedOnly.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked)
                        getString(R.string.main_activity_downloaded_only)
                    else
                        getString(
                                R.string.main_activity_also_not_downloaded), Toast.LENGTH_SHORT).show()
        }
        navigationView.setNavigationItemSelectedListener(this)
        if (!readShownPreference()) {
            drawerLayout.openDrawer(GravityCompat.START)
            saveShownPreference()
        }
    }

    private fun showOption(itemId: Int, title: String, menuItem: MenuItem) {
        when (itemId) {
            R.id.mnuOption1 -> {
                supportFragmentManager.transaction { replace(R.id.flContent, Option1Fragment(), title) }
                menuItem.isChecked = true
            }
            R.id.mnuOption2 -> {
                supportFragmentManager.transaction { replace(R.id.flContent, Option2Fragment(), title) }
                menuItem.isChecked = true
            }
            R.id.mnuOption3 -> {
                supportFragmentManager.transaction { replace(R.id.flContent, Option3Fragment(), title) }
                menuItem.isChecked = true
            }
            R.id.mnuDetail -> startActivity(Intent(this, DetailActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        actionBarDrawerToggle?.onOptionsItemSelected(item) ?: super.onOptionsItemSelected(item)

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        showOption(menuItem.itemId, menuItem.title.toString(), menuItem)
        drawerLayout.closeDrawers()
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    private fun saveShownPreference() {
        val sharedPref = applicationContext.getSharedPreferences(
                PREFERENCES_FILE, Context.MODE_PRIVATE)
        sharedPref.edit(true) { putBoolean(PREF_NAV_DRAWER_OPENED, true) }
    }

    private fun readShownPreference(): Boolean {
        val sharedPref = applicationContext.getSharedPreferences(
                PREFERENCES_FILE, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(PREF_NAV_DRAWER_OPENED, false)
    }

    override fun onToolbarAvailable(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        setTitle(title)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
        actionBarDrawerToggle?.let { drawerLayout.removeDrawerListener(actionBarDrawerToggle!!) }
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
                R.string.main_activity_navigation_drawer_open, R.string.main_activity_navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
    }

}
