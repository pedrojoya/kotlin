package es.iessaladillo.pedrojoya.pr092.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr092.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                replace(R.id.container, MainFragment.newInstance())
            }
        }
    }

    private fun initViews() {
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

}
