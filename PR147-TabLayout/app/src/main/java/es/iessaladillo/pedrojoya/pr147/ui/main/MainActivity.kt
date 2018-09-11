package es.iessaladillo.pedrojoya.pr147.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr147.R
import es.iessaladillo.pedrojoya.pr147.extensions.forEachIndexed
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        setupViewPager()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupViewPager() {
        val adapter = MainActivityAdapter(supportFragmentManager, this)
        viewPager.adapter = adapter
        tabLayout.run {
            // Not saved properly automatically on rotation.
            tabGravity = com.google.android.material.tabs.TabLayout.GRAVITY_FILL
            // ---
            setupWithViewPager(viewPager)
            forEachIndexed {index, tab ->
                tab?.setIcon(adapter.getPageIcon(index)) }
        }
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
