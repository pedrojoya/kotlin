package es.iessaladillo.pedrojoya.pr097

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnSave.setOnClickListener { _ ->
            startActivity(Intent(this, SaveActivity::class.java))
        }
        btnRetain.setOnClickListener { _ ->
            startActivity(Intent(this, RetainActivity::class.java))
        }
        btnAndroidState.setOnClickListener { _ ->
            startActivity(Intent(this, AndroidStateActivity::class.java))
        }
        btnStarter.setOnClickListener { _ ->
            startActivity(Intent(this, StarterActivity::class.java))
        }
        btnViewModel.setOnClickListener { _ ->
            startActivity(Intent(this, ViewModelActivity::class.java))
        }
    }

}
