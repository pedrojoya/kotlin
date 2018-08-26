package es.iessaladillo.pedrojoya.pr096.ui.result

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import es.iessaladillo.pedrojoya.pr096.BuildConfig
import es.iessaladillo.pedrojoya.pr096.R
import kotlinx.android.synthetic.main.activity_result.*

const val EXTRA_NOTIFICATION_CODE = "EXTRA_NOTIFICATION_CODE"
const val ACTION_VIEW = BuildConfig.APPLICATION_ID + ".action.VIEW"
const val ACTION_SEND = BuildConfig.APPLICATION_ID + ".action.SEND"
const val ACTION_DELETE = BuildConfig.APPLICATION_ID + ".action.DELETE"
const val ACTION_ANSWER = BuildConfig.APPLICATION_ID + ".action.ANSWER"

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        processIntent(intent)
    }

    private fun processIntent(intent: Intent?) {
        intent?.run {
            processAction(action, getIntExtra(EXTRA_NOTIFICATION_CODE, 0))
        }
    }

    private fun processAction(action: String?, notificationId: Int) {
        val notificationManager = NotificationManagerCompat.from(this)
        when (action) {
            ACTION_SEND -> {
                lblMessage.text = getString(R.string.main_activity_send)
                notificationManager.cancel(notificationId)
            }
            ACTION_DELETE -> {
                lblMessage.text = getString(R.string.main_activity_delete)
                notificationManager.cancel(notificationId)
            }
            ACTION_ANSWER -> {
                lblMessage.text = getString(R.string.main_activity_answer)
                notificationManager.cancel(notificationId)
            }
            else -> lblMessage.text = getString(R.string.ver)
        }
    }
}
