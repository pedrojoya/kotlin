package es.iessaladillo.pedrojoya.pr097.ui.state

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evernote.android.state.State
import es.iessaladillo.pedrojoya.pr097.R
import es.iessaladillo.pedrojoya.pr097.data.model.ScoreBoard
import kotlinx.android.synthetic.main.activity_score.*

class AndroidStateActivity : AppCompatActivity() {

    @State
    var scoreBoard = ScoreBoard()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        /* Not needed it we set StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true)
           in application onCreate()
        StateSaver.restoreInstanceState(this, savedInstanceState)
        */
        setupViews()
        showScore()
    }

    private fun setupViews() {
        btnIncrement.setOnClickListener { increment() }
    }

    private fun increment() {
        scoreBoard.incrementScore()
        showScore()
    }

    /* Not needed it we set StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true)
       in application onCreate()

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        StateSaver.saveInstanceState(this, outState)
    }

    */

    private fun showScore() {
        lblScore.text = scoreBoard.score.toString()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AndroidStateActivity::class.java))
        }
    }


}
