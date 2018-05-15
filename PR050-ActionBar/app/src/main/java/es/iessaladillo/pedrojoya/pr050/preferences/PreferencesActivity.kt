package es.iessaladillo.pedrojoya.pr050.preferences

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import es.iessaladillo.pedrojoya.pr050.R

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, PreferencesActivity::class.java))
        }

    }

}
