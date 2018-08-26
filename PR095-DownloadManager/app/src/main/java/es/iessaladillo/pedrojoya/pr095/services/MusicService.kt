package es.iessaladillo.pedrojoya.pr095.services

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import es.iessaladillo.pedrojoya.pr095.BuildConfig

const val EXTRA_SONG_PATH = "EXTRA_SONG_PATH"
const val ACTION_SONG_COMPLETED = BuildConfig.APPLICATION_ID + ".action.song_completed"

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
    }

    override fun onDestroy() {
        mediaPlayer.run {
            stop()
            release()
        }
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        prepareMediaPlayer(intent)
        return Service.START_NOT_STICKY
    }

    private fun prepareMediaPlayer(intent: Intent) {
        mediaPlayer.run {
            reset()
            isLooping = false
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setOnPreparedListener { mp -> mp.start() }
            setOnCompletionListener { _ ->
                LocalBroadcastManager.getInstance(this@MusicService).sendBroadcast(Intent
                (ACTION_SONG_COMPLETED))
                stopSelf()
            }
            try {
                setDataSource(intent.getStringExtra(EXTRA_SONG_PATH))
                prepareAsync()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    override fun onBind(arg0: Intent): IBinder? {
        // Not binded service.
        return null
    }

}
