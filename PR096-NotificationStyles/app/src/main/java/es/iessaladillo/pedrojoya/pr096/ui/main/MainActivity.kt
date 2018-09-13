package es.iessaladillo.pedrojoya.pr096.ui.main

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import es.iessaladillo.pedrojoya.pr096.DEFAULT_CHANNEL_ID
import es.iessaladillo.pedrojoya.pr096.HIGH_PRIORITY_CHANNEL_ID
import es.iessaladillo.pedrojoya.pr096.R
import es.iessaladillo.pedrojoya.pr096.ui.result.*
import kotlinx.android.synthetic.main.activity_main.*

private const val NC_BIG_TEXT = 1
private const val NC_BIG_PICTURE = 2
private const val NC_INBOX = 3
private const val NC_PROGRESS = 4
private const val NC_IND_PROGRESS = 5
private const val NC_HEADS_UP = 6

class MainActivity : AppCompatActivity() {

    private val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnBigTextStyle.setOnClickListener { notifyBigText() }
        btnBigPictureStyle.setOnClickListener { notifyBigPicture() }
        btnInboxStyle.setOnClickListener { notifyInbox() }
        btnProgressBar.setOnClickListener { notifyProgressBar() }
        btnIndeterminateProgress.setOnClickListener { notifyIndeterminateProgress() }
        btnHeadsUp.setOnClickListener { notifyHeadsUp() }
    }

    private fun notifyBigText() {
        val b = getBasicBuilder(DEFAULT_CHANNEL_ID)
        setBigTextStyle(b)
        addDefaultAction(b)
        addSendActionButton(b)
        addDeleteActionButton(b)
        notificationManager.notify(NC_BIG_TEXT, b.build())
    }

    private fun notifyBigPicture() {
        val b = getBasicBuilder(DEFAULT_CHANNEL_ID)
        val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.sunset)
        b.setLargeIcon(bigPicture)
        setBigPictureStyle(b, bigPicture)
        addDefaultAction(b)
        notificationManager.notify(NC_BIG_PICTURE, b.build())
    }

    private fun notifyInbox() {
        val b = getBasicBuilder(DEFAULT_CHANNEL_ID)
        setInbotStyle(b)
        addDefaultAction(b)
        notificationManager.notify(NC_INBOX, b.build())
    }

    private fun notifyProgressBar() {
        ProgressTask(this).execute()
    }

    private fun notifyIndeterminateProgress() {
        IndeterminateProgressTask(this).execute()
    }

    private fun notifyHeadsUp() {
        val b = getBasicBuilder(HIGH_PRIORITY_CHANNEL_ID)
        b.priority = NotificationManagerCompat.IMPORTANCE_HIGH
        addAnswerActionButton(b)
        addDefaultAction(b)
        notificationManager.notify(NC_HEADS_UP, b.build())
    }

    private fun getBasicBuilder(channelId: String): NotificationCompat.Builder =
            NotificationCompat.Builder(this, channelId).apply {
                setSmallIcon(R.drawable.ic_notifications_black_24dp)
                setContentTitle(getString(R.string.content_title))
                setContentText(getString(R.string.content_text))
                setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_LIGHTS)
                setAutoCancel(true)
            }

    private fun setBigTextStyle(b: NotificationCompat.Builder) {
        b.setStyle(NotificationCompat.BigTextStyle()
                .setBigContentTitle(getString(R.string.big_content_title))
                .bigText(getString(R.string.big_text))
                .setSummaryText(getString(R.string.summary_text)))
    }

    private fun setBigPictureStyle(b: NotificationCompat.Builder, bitPicture: Bitmap) {
        b.setStyle(NotificationCompat.BigPictureStyle()
                .setBigContentTitle(getString(R.string.big_content_title))
                .bigPicture(bitPicture)
                .bigLargeIcon(null)
                .setSummaryText(getString(R.string.summary_text)))
    }

    private fun setInbotStyle(b: NotificationCompat.Builder) {
        b.setStyle(NotificationCompat.InboxStyle()
                .setBigContentTitle(getString(R.string.big_content_title))
                .setSummaryText(getString(R.string.summary_text))
                .also {
                    for (i in 0..4) {
                        it.addLine(getString(R.string.line, i + 1))
                    }
                })
    }

    private fun addDefaultAction(b: NotificationCompat.Builder) {
        val viewIntent = Intent(this, ResultActivity::class.java).apply {
            action = ACTION_VIEW
        }
        val viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0)
        b.setContentIntent(viewPendingIntent)
    }

    private fun addSendActionButton(b: NotificationCompat.Builder) {
        val sendIntent = Intent(this, ResultActivity::class.java).apply {
            action = ACTION_SEND
            putExtra(EXTRA_NOTIFICATION_CODE, NC_BIG_TEXT)
        }
        val sendPendingIntent = PendingIntent.getActivity(this, 0, sendIntent, 0)
        b.addAction(R.drawable.ic_send_black_24dp, getString(R.string.main_activity_send), sendPendingIntent)
    }

    private fun addDeleteActionButton(b: NotificationCompat.Builder) {
        val deleteIntent = Intent(this, ResultActivity::class.java).apply {
            action = ACTION_DELETE
            putExtra(EXTRA_NOTIFICATION_CODE, NC_BIG_TEXT)
        }
        val deletePendingIntent = PendingIntent.getActivity(this, 0, deleteIntent, 0)
        b.addAction(R.drawable.ic_delete_black_24dp, getString(R.string.main_activity_delete),
                deletePendingIntent)
    }

    private fun addAnswerActionButton(b: NotificationCompat.Builder) {
        val answerIntent = Intent(this, ResultActivity::class.java).apply {
            action = ACTION_ANSWER
            putExtra(EXTRA_NOTIFICATION_CODE, NC_HEADS_UP)
        }
        val answerPendingIntent = PendingIntent.getActivity(this, 0, answerIntent, 0)
        b.addAction(R.drawable.ic_send_black_24dp, getString(R.string.main_activity_answer),
                answerPendingIntent)
    }

    private class ProgressTask(context: Context) : AsyncTask<Void, Int, Void?>() {

        private val maxSteps = 10

        @SuppressLint("StaticFieldLeak")
        private val context: Context = context.applicationContext
        private val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context.applicationContext)
        private val b: NotificationCompat.Builder =
                NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(context.getString(R.string.main_activity_progress))
                        .setContentText(context.getString(R.string.main_activity_updating))
                        .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_LIGHTS)
                        .setOnlyAlertOnce(true)
                        .setProgress(maxSteps, 0, false)

        override fun onPreExecute() {
            super.onPreExecute()
            notificationManager.notify(NC_PROGRESS, b.build())
        }

        override fun doInBackground(vararg voids: Void): Void? {
            for (i in 0 until maxSteps) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                publishProgress(i + 1)
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val step = values[0]!!
            // Update notification.
            b.setProgress(maxSteps, step, false).setContentText(
                    context.getString(R.string.main_activity_updating, step, maxSteps))
            notificationManager.notify(NC_PROGRESS, b.build())
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            // Final notification update.
            b.setContentText(context.getString(R.string.main_activity_update_finished))
                    .setTicker(context.getString(R.string.main_activity_update_finished))
                    .setProgress(0, 0, false)
                    .setAutoCancel(true)
            val viewIntent = Intent(context, ResultActivity::class.java)
            viewIntent.action = ACTION_VIEW
            val viewPendingIntent = PendingIntent.getActivity(context, 0, viewIntent,
                    0)
            b.setContentIntent(viewPendingIntent)
            notificationManager.notify(NC_PROGRESS, b.build())
        }

    }

    @SuppressLint("StaticFieldLeak")
    private class IndeterminateProgressTask(context: Context) : AsyncTask<Void, Void, Void?>() {

        private val context: Context = context.applicationContext
        private val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        private val b: NotificationCompat.Builder =
                NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setContentTitle(context.getString(R.string.main_activity_progress))
                    .setContentText(context.getString(R.string
                            .main_activity_indeterminate_progress))
                    .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_LIGHTS)
                    .setOnlyAlertOnce(true)
                    .setProgress(0, 0, true)

        override fun onPreExecute() {
            super.onPreExecute()
            notificationManager.notify(NC_IND_PROGRESS, b.build())
        }

        override fun doInBackground(vararg voids: Void): Void? {
            try {
                Thread.sleep((10 * 1000).toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            // Final notification update.
            b.setContentText(context.getString(R.string.main_activity_update_finished))
                    .setProgress(0, 0, false)
                    .setAutoCancel(true)
            val viewIntent = Intent(context, ResultActivity::class.java)
            viewIntent.action = ACTION_VIEW
            val viewPendingIntent = PendingIntent.getActivity(context, 0, viewIntent,
                    0)
            b.setContentIntent(viewPendingIntent)
            notificationManager.notify(NC_IND_PROGRESS, b.build())
        }

    }

}
