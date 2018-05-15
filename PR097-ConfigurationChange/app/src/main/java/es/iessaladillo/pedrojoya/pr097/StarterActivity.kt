package es.iessaladillo.pedrojoya.pr097

import activitystarter.ActivityStarter
import activitystarter.Arg
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_count.*

class StarterActivity : AppCompatActivity() {

    @Arg(optional = true)
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        ActivityStarter.fill(this, savedInstanceState)
        btnIncrement.setOnClickListener {
            count++
            lblCount.text = count.toString()
        }
        lblCount.text = count.toString()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        ActivityStarter.save(this, outState)
    }

}
