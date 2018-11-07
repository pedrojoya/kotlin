package es.iessaladillo.pedrojoya.pr097.ui.save

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr097.R
import es.iessaladillo.pedrojoya.pr097.data.model.ScoreBoard
import kotlinx.android.synthetic.main.activity_score.*

const val STATE_SCORE_BOARD = "STATE_SCORE_BOARD"

class SaveActivity : AppCompatActivity() {

    private lateinit var scoreBoard: ScoreBoard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        restoreSavedInstanceState(savedInstanceState)
        setupViews()
        showScore()
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        scoreBoard = savedInstanceState?.getParcelable(STATE_SCORE_BOARD) ?: ScoreBoard()
    }

    private fun setupViews() {
        btnIncrement.setOnClickListener { increment() }
    }

    private fun increment() {
        scoreBoard.incrementScore()
        showScore()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(STATE_SCORE_BOARD, scoreBoard)
    }

    private fun showScore() {
        lblScore.text = scoreBoard.score.toString()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SaveActivity::class.java))
        }
    }


}

