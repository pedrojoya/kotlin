package es.iessaladillo.pedrojoya.pr097

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evernote.android.state.State
import com.evernote.android.state.StateSaver
import kotlinx.android.synthetic.main.activity_count.*

class AndroidStateActivity : AppCompatActivity() {

    @State
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        StateSaver.restoreInstanceState(this, savedInstanceState)
        initViews()
        showCount()
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
        StateSaver.saveInstanceState(this, outState)
    }

    private fun showCount() {
        lblCount.text = count.toString()
    }

}
