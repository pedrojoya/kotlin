package pedrojoya.iessaladillo.es.pr248.ui.main

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import pedrojoya.iessaladillo.es.pr248.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            loadInitialFragment()
        }
    }

    private fun loadInitialFragment() {
        supportFragmentManager.commit {
            replace(R.id.flContent,
                    MainFragment.newInstance(), MainFragment::class.java.simpleName)
        }
    }

}
