package es.iessaldillo.pedrojoya.pr160.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import es.iessaldillo.pedrojoya.pr160.R
import es.iessaldillo.pedrojoya.pr160.base.OnFabClickListener
import es.iessaldillo.pedrojoya.pr160.extensions.onPageSelected
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        setupViewPager()
        setupFab()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Title must be shown in toolbar, because in collapsingToolbar whould show over the
        // tabLayout.
        supportActionBar?.title = title
        collapsingToolbar.isTitleEnabled = false
    }

    private fun setupViewPager() {
        mAdapter = MainActivityAdapter(supportFragmentManager, this@MainActivity)
        viewPager.run {
            adapter = mAdapter
            offscreenPageLimit = 2
        }
        tabLayout.run {
            setupWithViewPager(viewPager)
            tabGravity = com.google.android.material.tabs.TabLayout.GRAVITY_FILL
        }
        viewPager.onPageSelected { position ->
            updateHeader(position)
            updateFabState(position)
        }
    }

    private fun updateHeader(position: Int) {
        imgHeader.setImageResource(mAdapter.getPageHeader(position))
        imgHeader.invalidate()
    }

    private fun updateFabState(position: Int) {
        if (position == 1) fab.hide() else fab.show()
    }

    private fun setupFab() {
        fab.setOnClickListener { callFabOnFragment() }
    }

    private fun callFabOnFragment() {
        (mAdapter.getFragment(tabLayout.selectedTabPosition) as OnFabClickListener).onFabClick(fab)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.mnuSettings -> true
                else -> super.onOptionsItemSelected(item)
            }

}
