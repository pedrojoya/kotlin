package es.iessaladillo.pedrojoya.pr086.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr086.R

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Load initial fragment.
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.flContent, MainFragment.newInstance(), MainFragment::class.java.simpleName)
            }
        }
    }

}
