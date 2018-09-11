package pedrojoya.iessaladillo.es.pr104.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import pedrojoya.iessaladillo.es.pr104.R
import pedrojoya.iessaladillo.es.pr104.ui.secondary.SecondaryActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.mnuNext -> {
                    SecondaryActivity.start(this); true }
                else -> super.onOptionsItemSelected(item)
    }

}
