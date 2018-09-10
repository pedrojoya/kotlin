package es.iessaladillo.pedrojoya.pr006.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr006.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showEvent(getString(R.string.main_activity_oncreate))
    }

    override fun onDestroy() {
        super.onDestroy()
        showEvent(getString(R.string.main_activity_ondestroy))
    }

    override fun onPause() {
        super.onPause()
        showEvent(getString(R.string.main_activity_onpause))
    }

    override fun onResume() {
        super.onResume()
        showEvent(getString(R.string.main_activity_onresume))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        showEvent(getString(R.string.main_activity_onsaveinstancestate))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        showEvent(getString(R.string.main_activity_onrestoreinstancestate))
    }

    override fun onStart() {
        super.onStart()
        showEvent(getString(R.string.main_activity_onstart))
    }

    override fun onStop() {
        super.onStop()
        showEvent(getString(R.string.main_activity_onstop))
    }

    override fun onRestart() {
        super.onRestart()
        showEvent(getString(R.string.main_activity_onrestart))
    }

    private fun showEvent(event: String) {
        Log.d(getString(R.string.app_name), event)
        lblEvents.append(event)
    }

}
