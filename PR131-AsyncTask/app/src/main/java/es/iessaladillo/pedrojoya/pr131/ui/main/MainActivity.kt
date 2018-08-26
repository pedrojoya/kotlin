package es.iessaladillo.pedrojoya.pr131.ui.main

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr131.R
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

const val TAG = "ASYNKTASK"

class MainActivity : AppCompatActivity() {

    private var secundaryTask: SecundaryTask? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnStart.setOnClickListener { start() }
        btnCancel.setOnClickListener { cancel() }
        updateViews(0)
    }

    private fun start() {
        secundaryTask = SecundaryTask(this)
        secundaryTask!!.execute(resources.getInteger(R.integer.activity_main_steps))
    }

    private fun cancel() {
        secundaryTask!!.cancel(true)
    }

    override fun onDestroy() {
        secundaryTask?.run {
            // Try and comment this line. Task will go on executing but changes
            // will be send to an old instance of the activity.
            cancel(true)
        }
        super.onDestroy()
    }

    private fun updateViews(step: Int) {
        Log.d(TAG, "Paso $step")
        val working = (step > 0 && step < resources.getInteger(
                R.integer.activity_main_steps) && secundaryTask != null
                && secundaryTask!!.status == AsyncTask.Status.RUNNING)
        if (!working) {
            lblMessage.text = ""
        }
        prbBar.progress = step
        lblMessage.text = getString(R.string.activity_main_lblMessage, step,
                resources.getInteger(R.integer.activity_main_steps))
        btnStart.isEnabled = !working
        btnCancel.isEnabled = working
        prbBar.visibility = if (working) View.VISIBLE else View.INVISIBLE
        lblMessage.visibility = if (working) View.VISIBLE else View.INVISIBLE
        prbCircle.visibility = if (working) View.VISIBLE else View.INVISIBLE
    }

    class SecundaryTask(mainActivity: MainActivity) : AsyncTask<Int, Int, Int>() {

        private val mainActivityWeakReference: WeakReference<MainActivity> = WeakReference(mainActivity)
        private var maxSteps: Int = 0

        override fun onPreExecute() {
            mainActivityWeakReference.get()?.run {
                updateViews(0)
            }
        }

        override fun doInBackground(vararg params: Int?): Int {
            maxSteps = params[0]?:0
            var i = 0
            while (i < maxSteps && !isCancelled) {
                doWork()
                publishProgress(i + 1)
                i++
            }
            return maxSteps
        }

        override fun onProgressUpdate(vararg values: Int?) {
            mainActivityWeakReference.get()?.run {
                updateViews(values[0]?:0)
            }
        }

        override fun onCancelled() {
            Log.d(TAG, "Task cancelled")
            mainActivityWeakReference.get()?.run {
                updateViews(0)
            }
        }

        override fun onPostExecute(result: Int) {
            mainActivityWeakReference.get()?.run {
                updateViews(maxSteps)
            }
        }

        private fun doWork() {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

    }

}
