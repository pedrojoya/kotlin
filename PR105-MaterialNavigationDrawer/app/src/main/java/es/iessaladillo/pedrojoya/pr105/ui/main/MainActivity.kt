package es.iessaladillo.pedrojoya.pr105.ui.main

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.pr105.R
import es.iessaladillo.pedrojoya.pr105.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.pr105.extensions.loadUrl
import es.iessaladillo.pedrojoya.pr105.ui.detail.DetailActivity
import es.iessaladillo.pedrojoya.pr105.ui.main.option1.Option1Fragment
import es.iessaladillo.pedrojoya.pr105.ui.main.option2.Option2Fragment
import es.iessaladillo.pedrojoya.pr105.ui.main.option3.Option3Fragment
import kotlinx.android.synthetic.main.activity_main.*

private const val PREFERENCES_FILE = "prefs"
private const val PREF_NAV_DRAWER_OPENED = "navdrawerOpened"

class MainActivity : AppCompatActivity(), OnToolbarAvailableListener {

    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private val viewModel: MainActivityViewModel by viewModels { MainActivityViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        observeCurrentOption()
        // if just lunched, select default menu item in drawer.
        if (savedInstanceState == null) {
            navigateToStartOption()
        }
    }

    private fun navigateToStartOption() {
        supportFragmentManager.commit {
            replace(R.id.flContent, Option1Fragment.newInstance(), Option1Fragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    private fun setupViews() {
        setupNavigationDrawer()
        addOnBackPressedCallback { closeDrawerIfOpen() }
    }

    private fun closeDrawerIfOpen(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }

    private fun setupNavigationDrawer() {
        val imgProfile: ImageView = ViewCompat.requireViewById(navigationView.getHeaderView(0), R.id.imgProfile)
        imgProfile.loadUrl("http://lorempixel.com/200/200/people/")
        val swDownloadedOnly = navigationView.menu.findItem(
                R.id.mnuDownloaded).actionView as SwitchCompat
        swDownloadedOnly.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked)
                getString(R.string.main_activity_downloaded_only)
            else
                getString(
                        R.string.main_activity_also_not_downloaded), Toast.LENGTH_SHORT).show()
        }
        navigationView.setNavigationItemSelectedListener { onNavItemSelected(it) }
        if (!readShownPreference()) {
            drawerLayout.openDrawer(GravityCompat.START)
            saveShownPreference()
        }
    }

    private fun navigateToOption(menuItem: MenuItem) {
        val currentOption = viewModel.currentOption.value
        if (currentOption == null || currentOption != menuItem.itemId) {
            when (menuItem.itemId) {
                R.id.mnuOption1 -> {
                    replaceFragment(Option1Fragment.newInstance(), Option1Fragment::class.java.simpleName)
                    menuItem.isChecked = true
                }
                R.id.mnuOption2 -> {
                    replaceFragment(Option2Fragment.newInstance(), Option2Fragment::class.java.simpleName)
                    menuItem.isChecked = true
                }
                R.id.mnuOption3 -> {
                    replaceFragment(Option3Fragment.newInstance(), Option3Fragment::class.java.simpleName)
                    menuItem.isChecked = true
                }
                R.id.mnuDetail -> DetailActivity.start(this)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.commit {
            replace(R.id.flContent, fragment, tag)
            addToBackStack(tag)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            (actionBarDrawerToggle?.onOptionsItemSelected(item) ?: false) ||
                    super.onOptionsItemSelected(item)

    private fun onNavItemSelected(menuItem: MenuItem): Boolean {
        navigateToOption(menuItem)
        drawerLayout.closeDrawers()
        return true
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

    // Fragment sends toolbar to activity so it can setup it.
    override fun onToolbarAvailable(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
        actionBarDrawerToggle?.let { drawerLayout.removeDrawerListener(actionBarDrawerToggle!!) }
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
                R.string.main_activity_navigation_drawer_open, R.string.main_activity_navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
    }

    private fun observeCurrentOption() {
        viewModel.currentOption.observe(this, Observer { option ->
            navigationView.menu.findItem(option)?.isChecked = true
        })
    }

}
