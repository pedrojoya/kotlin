package es.iessaladillo.pedrojoya.pr097

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr097.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_count.*

class ViewModelActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by viewModelProvider()

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
        viewModel.increment()
        showCount()
    }

    private fun showCount() {
        lblCount.text = viewModel.count.toString()
    }

}

