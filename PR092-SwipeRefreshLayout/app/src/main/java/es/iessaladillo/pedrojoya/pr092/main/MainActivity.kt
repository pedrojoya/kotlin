package es.iessaladillo.pedrojoya.pr092.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr092.R
import es.iessaladillo.pedrojoya.pr092.extensions.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setupToolbar()
        if (savedInstanceState == null) {
            replaceFragment(R.id.container, MainFragment())
        }
    }

    private fun initViews() {
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

}
