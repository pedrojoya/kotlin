package es.iessaladillo.pedrojoya.pr045.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr045.R

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            loadInitialFragment()
        }
    }

    private fun loadInitialFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flContent, MainFragment.newInstance(), MainFragment::class.java.simpleName)
                .commit()
    }

}