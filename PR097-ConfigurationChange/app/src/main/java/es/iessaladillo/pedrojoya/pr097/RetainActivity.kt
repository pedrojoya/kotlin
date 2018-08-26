package es.iessaladillo.pedrojoya.pr097

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_count.*

class RetainActivity : AppCompatActivity() {

    private val state: State by lazy { lastCustomNonConfigurationInstance as State? ?: State() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        initViews()
        showCount()
    }

    private fun initViews() {
        btnIncrement.setOnClickListener { increment() }
    }

    private fun increment() {
        state.increment()
        showCount()
    }

    override fun onRetainCustomNonConfigurationInstance(): State = state

    private fun showCount() {
        lblCount.text = state.count.toString()
    }

    class State {
        internal var count = 0
            private set

        internal fun increment() {
            count++
        }
    }

}
