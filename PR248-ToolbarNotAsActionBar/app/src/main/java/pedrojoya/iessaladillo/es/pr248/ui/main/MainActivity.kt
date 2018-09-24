package pedrojoya.iessaladillo.es.pr248.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import pedrojoya.iessaladillo.es.pr248.R
import pedrojoya.iessaladillo.es.pr248.ui.secondary.SecondaryActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.run {
            setTitle(R.string.app_name)
            inflateMenu(R.menu.activity_main)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.mnuNext -> {
                        SecondaryActivity.start(this@MainActivity)
                        true
                    }
                    else -> false
                }
            }
        }
    }

}
