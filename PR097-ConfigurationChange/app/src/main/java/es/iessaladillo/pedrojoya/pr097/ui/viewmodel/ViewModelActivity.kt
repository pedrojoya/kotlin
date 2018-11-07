package es.iessaladillo.pedrojoya.pr097.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr097.R
import es.iessaladillo.pedrojoya.pr097.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_score.*

class ViewModelActivity : AppCompatActivity() {

    private val viewModel: ViewModelActivityViewModel by viewModelProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        setupViews()
        showScore()
    }

    private fun setupViews() {
        btnIncrement.setOnClickListener { increment() }
    }

    private fun increment() {
        viewModel.increment()
        showScore()
    }

    private fun showScore() {
        lblScore.text = viewModel.scoreBoard.score.toString()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ViewModelActivity::class.java))
        }
    }

}
