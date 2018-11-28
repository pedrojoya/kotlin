package es.iessaladillo.pedrojoya.pr234.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr234.R

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            supportFragmentManager.commit {
                replace(R.id.flContent, MainFragment.newInstance(),
                        TAG_MAIN_FRAGMENT)
            }
        }
    }

}