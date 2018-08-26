package es.iessaladillo.pedrojoya.pr097

import androidx.lifecycle.ViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr097.extensions.getViewModel
import kotlinx.android.synthetic.main.activity_count.*

class ViewModelActivity : AppCompatActivity() {

    private lateinit var viewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        viewModel = getViewModel()
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

class ActivityViewModel : ViewModel() {

    internal var count = 0
        private set

    internal fun increment() {
        count++
    }

}