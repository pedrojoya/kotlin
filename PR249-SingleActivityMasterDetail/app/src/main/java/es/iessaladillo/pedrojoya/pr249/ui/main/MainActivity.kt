package es.iessaladillo.pedrojoya.pr249.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr249.R
import es.iessaladillo.pedrojoya.pr249.ui.detail.DetailFragment
import es.iessaladillo.pedrojoya.pr249.ui.list.ListFragment

class MainActivity : AppCompatActivity(), ListFragment.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // We load initial fragment
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.flContent, ListFragment.newInstance(), ListFragment::class.java.simpleName)
            }
        }
    }

    override fun onItemSelected(item: String) {
        supportFragmentManager.commit {
            replace(R.id.flContent, DetailFragment.newInstance(item), DetailFragment::class.java.simpleName)
            addToBackStack(DetailFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
