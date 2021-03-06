package es.iessaladillo.pedrojoya.pr082.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr082.R

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentById(R.id.flContent) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flContent, MainFragment.newInstance(), TAG_MAIN_FRAGMENT)
            }
        }
    }

}
