package es.iessaladillo.pedrojoya.pr013

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.RESTART
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr013.extensions.onAnimationEnd
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

private const val rightAnswer = "Blanco"
private const val TIMEOUT_INITIAL: Long = 5000 // miliseconds

class MainActivity : AppCompatActivity() {

    private val objectAnimator by lazy {
        ObjectAnimator.ofFloat(vTimeout, View.ROTATION, 0.0f,
                TIMEOUT_INITIAL / 1000 * 360.0f).apply {
            duration = TIMEOUT_INITIAL
            repeatMode = RESTART
            repeatCount = INFINITE
            interpolator = LinearInterpolator()
        }
    }

    private val mAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, R.layout.activity_main_item, R.id.lblAnswer,
                answers)
    }

    private val answers: ArrayList<String> = arrayListOf("Marrón", "Verde", rightAnswer,
            "Negro")
    private var score = 100

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnCheck.setOnClickListener { checkAnswer() }
        lstAnswers.apply {
            adapter = mAdapter
            setOnItemClickListener { _, _, _, _ -> btnCheck.isEnabled = true }
        }
    }

    override fun onResume() {
        startTimeout()
        super.onResume()
    }

    private fun startTimeout() {
        animateTimeout()
        object : CountDownTimer(TIMEOUT_INITIAL, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                lblTimeout.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                objectAnimator.end()
                showCheck()
            }

        }.start()
    }

    private fun showCheck() {
        btnCheck.visibility = View.VISIBLE
        lblTimeout.visibility = View.GONE
        vTimeout.visibility = View.GONE
    }

    private fun animateTimeout() {
        objectAnimator.start()
    }

    private fun checkAnswer() {
        val selectedPosition = lstAnswers.checkedItemPosition
        val selectedAnswer = lstAnswers.getItemAtPosition(selectedPosition) as String
        if (selectedAnswer == rightAnswer) {
            lblScore.text = getString(R.string.main_activity_score, "+", score)
            animateScore(true)
        } else {
            val reduction = if (score == 100) 50 else 25
            score -= reduction
            lblScore.text = getString(R.string.main_activity_score, "-", reduction)
            animateScore(false)
            lstAnswers.setItemChecked(selectedPosition, false)
            mAdapter.remove(selectedAnswer)
            mAdapter.notifyDataSetChanged()
            btnCheck.isEnabled = false
        }
    }

    private fun animateScore(right: Boolean) {
        lblScore.apply {
            setBackgroundResource(
                    if (right) R.drawable.activity_main_lblscore_background
                    else R.drawable.activity_main_lblscore_background_wrong
            )
            visibility = View.VISIBLE
            animate()
                    .scaleX(1.2f)
                    .scaleY(1.2f)
                    .translationY(30f)
                    .setDuration(3000)
                    .setInterpolator(BounceInterpolator())
                    .onAnimationEnd {
                        if (!right) {
                            resetScorePosition()
                        } else {
                            finish()
                        }
                    }
        }
    }

    private fun resetScorePosition() {
        lblScore.apply {
            visibility = View.INVISIBLE
            scaleX = 1f
            scaleY = 1f
            translationX = 0f
            translationY = 0f
        }
    }

}
