package pedrojoya.iessaladillo.es.pr248.ui.secondary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import pedrojoya.iessaladillo.es.pr248.R


class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secundary)
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.run {
            setTitle(R.string.secundary_activity_title)
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, SecondaryActivity::class.java))
        }

    }

}
