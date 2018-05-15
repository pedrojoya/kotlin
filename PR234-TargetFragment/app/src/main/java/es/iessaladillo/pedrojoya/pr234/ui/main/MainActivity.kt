package es.iessaladillo.pedrojoya.pr234.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import es.iessaladillo.pedrojoya.pr234.R
import es.iessaladillo.pedrojoya.pr234.extensions.findFragmentByTag
import es.iessaladillo.pedrojoya.pr234.extensions.replaceFragment

private const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            replaceFragment(R.id.flContent, MainFragment.newInstance(), TAG_MAIN_FRAGMENT)
        }
    }

}