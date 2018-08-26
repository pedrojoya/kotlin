package es.iessaladillo.pedrojoya.pr045.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        vpPages.apply {
            adapter = MainActivityAdapter(this@MainActivity)
            currentItem = DEFAULT_PAGE
        }
    }

}