package es.iessaladillo.pedrojoya.pr089.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import es.iessaladillo.pedrojoya.pr089.R
import es.iessaladillo.pedrojoya.pr089.services.ACTION_SUMMATION
import es.iessaladillo.pedrojoya.pr089.services.EXTRA_NUMBER
import es.iessaladillo.pedrojoya.pr089.services.EXTRA_RESULT
import es.iessaladillo.pedrojoya.pr089.services.SummationService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.hasExtra(EXTRA_RESULT)) {
                    val number = intent.getLongExtra(EXTRA_NUMBER, 0)
                    val result = intent.getLongExtra(EXTRA_RESULT, 1)
                    Toast.makeText(this@MainActivity, "Summation of $number = $result",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initViews() {
        btnCalculate.setOnClickListener { _ -> calculate() }
    }

    private fun calculate() {
        try {
            val number = java.lang.Long.valueOf(txtInteger.text.toString())
            SummationService.start(this, number)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ACTION_SUMMATION)
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(
                broadcastReceiver, intentFilter)
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(
                broadcastReceiver)
        super.onPause()
    }

}
