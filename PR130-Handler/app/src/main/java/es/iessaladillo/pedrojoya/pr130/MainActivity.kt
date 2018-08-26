package es.iessaladillo.pedrojoya.pr130

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

private const val TAG = "HANDLER"
private const val onPreExecute = 0
private const val onProgressUpdate = 1
private const val onPostExecute = 2
private const val onCancelled = 3
private const val NUMBER_OF_TASKS = 10

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityHandler: MainActivityHandler
    private var secundaryThread: Thread? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Handler associated with current thread. It will work with the message queue of
        // the current activity.
        mainActivityHandler = MainActivityHandler(this)
        initViews()
    }

    private fun initViews() {
        btnStart.setOnClickListener { _ -> start() }
        btnCancel.setOnClickListener { _ -> cancel() }
        resetViews()
    }

    private fun start() {
        btnStart.isEnabled = false
        btnCancel.isEnabled = true
        secundaryThread = Thread(Task(mainActivityHandler))
        secundaryThread?.start()
    }

    private fun cancel() {
        secundaryThread?.interrupt()
    }

    private fun showProgress() {
        prbBar.visibility = View.VISIBLE
        lblMessage.text = getString(R.string.main_activity_task, 0, NUMBER_OF_TASKS)
        lblMessage.visibility = View.VISIBLE
        prbCircular.visibility = View.VISIBLE
    }

    private fun updateProgress(progress: Int) {
        lblMessage.text = getString(R.string.main_activity_task, progress, NUMBER_OF_TASKS)
        prbBar.progress = progress
    }

    private fun showDoneTasks(tasks: Int) {
        lblMessage.text = resources.getQuantityString(R.plurals.main_activity_tasks_done, tasks, tasks)
    }

    private fun resetViews() {
        lblMessage.text = ""
        prbBar.visibility = View.INVISIBLE
        prbBar.progress = 0
        prbCircular.visibility = View.INVISIBLE
        prbCircular.progress = 0
        btnStart.isEnabled = true
        btnCancel.isEnabled = false
    }

    private fun showCancelled() {
        Toast.makeText(this, R.string.main_activity_task_cancelled, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        // Try and comment this line. The thread will go on executing but the instance of the
        // activity associated with the handler has no window associated where to show results.
        secundaryThread?.interrupt()
        super.onDestroy()
    }

    private class Task(private val mainActivityHandler: MainActivityHandler) : Runnable {

        override fun run() {
            sendInitialMessage()
            var i = 0
            while (i < NUMBER_OF_TASKS && !Thread.currentThread().isInterrupted) {
                if (Thread.currentThread().isInterrupted) {
                    sendCancelledMessage()
                    return
                }
                try {
                    work()
                } catch (e: InterruptedException) {
                    sendCancelledMessage()
                    return
                }
                sendProgressMessage(i)
                i++
            }
            sendFinalMessage()
        }

        private fun sendInitialMessage() {
            mainActivityHandler.sendMessage(Message().apply {
                what = onPreExecute
            })
        }

        private fun sendProgressMessage(i: Int) {
            Log.d(TAG, "Tarea $i")
            mainActivityHandler.sendMessage(Message().apply {
                what = onProgressUpdate
                arg1 = i + 1
            })
        }

        private fun sendFinalMessage() {
            mainActivityHandler.sendMessage(Message().apply {
                what = onPostExecute
                arg1 = NUMBER_OF_TASKS
            })
        }

        private fun sendCancelledMessage() {
            mainActivityHandler.sendMessage(Message().apply {
                what = onCancelled
            })
        }

        // Working for 1 second simulation.
        private fun work() {
            Thread.sleep(1000)
        }

    }

    // Handler only has a weak reference to the activity so as not to produce memory leaks.
    private class MainActivityHandler(activity: MainActivity) : Handler() {

        private val activityWeakReference: WeakReference<MainActivity> = WeakReference(activity)

        override fun handleMessage(message: Message) {
            activityWeakReference.get()?.run {
                when (message.what) {
                    onPreExecute -> showProgress()
                    onProgressUpdate -> {
                        val progress = message.arg1
                        Log.d(TAG, "Mensaje tarea $progress recibida")
                        updateProgress(progress)
                    }
                    onPostExecute -> {
                        val tasks = message.arg1
                        showDoneTasks(tasks)
                        resetViews()
                    }
                    onCancelled -> {
                        showCancelled()
                        resetViews()
                    }
                }
            }
        }

    }

}
