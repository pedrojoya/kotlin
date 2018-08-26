package es.iessaladillo.pedrojoya.pr097

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_count.*

const val STATE_COUNT = "STATE_COUNT"

class SaveActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        restoreSavedInstanceState(savedInstanceState)
        initViews()
        showCount()
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        count = savedInstanceState?.getInt(STATE_COUNT, 0) ?: 0
    }

    private fun initViews() {
        btnIncrement.setOnClickListener { increment() }
    }

    private fun increment() {
        count++
        showCount()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_COUNT, count)
    }

    private fun showCount() {
        lblCount.text = count.toString()
    }

}

