package es.iessaladillo.pedrojoya.pr132.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr132.R

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentByTag(MainFragment::class.java.simpleName) == null) {
            supportFragmentManager.commit {
                replace(R.id.flContainer, MainFragment.newInstance(), MainFragment::class.java.simpleName)
            }
        }
    }

}
