package es.iessaladillo.pedrojoya.pr212.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr212.R
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

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
            supportFragmentManager.transaction { replace(R.id.flContent, MainFragment.newInstance(), TAG_MAIN_FRAGMENT) }
        }
    }

}
