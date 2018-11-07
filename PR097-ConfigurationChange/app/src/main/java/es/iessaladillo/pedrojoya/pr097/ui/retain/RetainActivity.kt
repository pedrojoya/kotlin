package es.iessaladillo.pedrojoya.pr097.ui.retain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr097.R
import es.iessaladillo.pedrojoya.pr097.data.model.ScoreBoard
import kotlinx.android.synthetic.main.activity_score.*

class RetainActivity : AppCompatActivity() {

    private val state: State by lazy { lastCustomNonConfigurationInstance as State? ?: State() }

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
        state.scoreBoard.incrementScore()
        showScore()
    }

    override fun onRetainCustomNonConfigurationInstance(): State = state

    private fun showScore() {
        lblScore.text = state.scoreBoard.score.toString()
    }

    class State {
        val scoreBoard: ScoreBoard = ScoreBoard()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RetainActivity::class.java))
        }
    }

}
