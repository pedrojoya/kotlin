package es.iessaladillo.pedrojoya.pr006

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

const val STATE_EVENT_LIST = "STATE_EVENT_LIST"

class MainActivity : AppCompatActivity() {

    private var events: String = ""

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
        outState.putString(STATE_EVENT_LIST, events)
        showEvent(getString(R.string.main_activity_onsaveinstancestate))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        events = savedInstanceState.getString(STATE_EVENT_LIST)
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
        events += event
        lblEvents.text = events
    }

}
