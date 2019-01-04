package es.iessaladillo.pedrojoya.pr211.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr211.R
import es.iessaladillo.pedrojoya.pr211.ui.list.ListFragment
import kotlinx.android.synthetic.main.activity_main.*

const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        if (savedInstanceState == null) {
            navigateToStartFragment()
        }
    }

    private fun setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        setSupportActionBar(toolbar)
    }

    private fun navigateToStartFragment() {
        supportFragmentManager.commit {
            replace(R.id.flContent, ListFragment.newInstance(), TAG_MAIN_FRAGMENT)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

}
