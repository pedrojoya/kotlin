package es.iessaladillo.pedrojoya.pr097.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr097.R
import es.iessaladillo.pedrojoya.pr097.ui.retain.RetainActivity
import es.iessaladillo.pedrojoya.pr097.ui.save.SaveActivity
import es.iessaladillo.pedrojoya.pr097.ui.state.AndroidStateActivity
import es.iessaladillo.pedrojoya.pr097.ui.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        btnSave.setOnClickListener { SaveActivity.start(this@MainActivity) }
        btnRetain.setOnClickListener { RetainActivity.start(this@MainActivity) }
        btnState.setOnClickListener { AndroidStateActivity.start(this@MainActivity) }
        btnViewModel.setOnClickListener { ViewModelActivity.start(this@MainActivity) }
    }

}
