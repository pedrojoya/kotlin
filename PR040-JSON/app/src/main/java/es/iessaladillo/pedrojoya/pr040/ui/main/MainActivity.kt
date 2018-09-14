package es.iessaladillo.pedrojoya.pr040.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr040.R
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if (supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flContainer, MainFragment(), TAG_MAIN_FRAGMENT)
            }
        }
    }

}
