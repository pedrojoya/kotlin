package es.iessaladillo.pedrojoya.pr045.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr045.R
import kotlinx.android.synthetic.main.activity_main.*

private const val DEFAULT_PAGE = 2

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        with (vpPages) {
            adapter = MainActivityAdapter(this@MainActivity)
            currentItem = DEFAULT_PAGE
        }
    }

}