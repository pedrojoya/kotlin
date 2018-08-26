package es.iessaladillo.pedrojoya.pr211.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.extensions.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        loadFragment()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun loadFragment() {
        if (supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            replaceFragment(R.id.flContent, MainFragment.newInstance(), TAG_MAIN_FRAGMENT)
        }
    }

}
