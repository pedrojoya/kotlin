package es.iessaladillo.pedrojoya.pr239.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.iessaladillo.pedrojoya.pr239.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initViews()
        viewModel.stockLiveData.observe(this, Observer<Int> { this.showPrice(it) })
    }

    private fun showPrice(price: Int?) {
        lblStockPrice.text = price.toString()
    }

    private fun initViews() {
        btnStart.setOnClickListener { _ -> viewModel.start() }
        btnStop.setOnClickListener { _ -> viewModel.stop() }
    }

}
