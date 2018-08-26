package es.iessaladillo.pedrojoya.pr095.ui.main

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.pr095.R
import es.iessaladillo.pedrojoya.pr095.data.local.Database
import es.iessaladillo.pedrojoya.pr095.data.local.Repository
import es.iessaladillo.pedrojoya.pr095.data.local.RepositoryImpl
import es.iessaladillo.pedrojoya.pr095.data.model.Song
import es.iessaladillo.pedrojoya.pr095.services.ACTION_SONG_COMPLETED
import es.iessaladillo.pedrojoya.pr095.services.EXTRA_SONG_PATH
import es.iessaladillo.pedrojoya.pr095.services.MusicService
import es.iessaladillo.pedrojoya.pr095.utils.newStandardDownloadsActivityIntent
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File

const val MP3_FILE_EXTENSION = ".mp3"

class MainFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private val repository: Repository = RepositoryImpl(Database)
    private val downloadManager: DownloadManager? by lazy {
        requireActivity().getSystemService<DownloadManager>()
    }
    private val localBroadcastManager: LocalBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(requireActivity())
    }
    private var downloadedSongBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            showDownloadState(intent)
        }
    }
    private var completedSongBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val nextSongPosition = (lstSongs.checkedItemPosition + 1) % lstSongs.count
            playSong(nextSongPosition)
        }
    }
    private val listAdapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter(requireActivity(), repository.querySongs(), lstSongs)
    }
    private val serviceIntent: Intent by lazy {
        Intent(requireActivity().applicationContext, MusicService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View?) {
        setupFab()
        setupListView(view)
    }

    private fun setupListView(view: View?) {
        lstSongs.run {
            adapter = listAdapter
            choiceMode = ListView.CHOICE_MODE_SINGLE
            setOnItemClickListener { _, _, position, _ -> playSong(position) }
            lstSongs.emptyView = lblEmptyView
            // API 21+ it will work with CoordinatorLayout.
            ViewCompat.setNestedScrollingEnabled(lstSongs, true)
        }
    }

    override fun onResume() {
        super.onResume()
        registerCompletedSongBroadcastReceiver()
        resgisterDownloadedSongBroadcastReceiver()
        showPlayingState()
    }

    private fun registerCompletedSongBroadcastReceiver() {
        localBroadcastManager.registerReceiver(completedSongBroadcastReceiver,
                IntentFilter(ACTION_SONG_COMPLETED))
    }

    private fun resgisterDownloadedSongBroadcastReceiver() {
        requireActivity().registerReceiver(downloadedSongBroadcastReceiver,
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    private fun showPlayingState() {
        if (lstSongs.checkedItemPosition == AdapterView.INVALID_POSITION) {
            showStopped()
        } else {
            showPlaying()
        }
    }

    override fun onPause() {
        super.onPause()
        localBroadcastManager.unregisterReceiver(completedSongBroadcastReceiver)
        requireActivity().unregisterReceiver(downloadedSongBroadcastReceiver)
    }

    private fun showDownloadState(intent: Intent) {
        val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0)
        val query = DownloadManager.Query()
        query.setFilterById(downloadId)
        val c = downloadManager!!.query(query)
        if (c != null && c.moveToFirst()) {
            val state = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))
            when (state) {
                DownloadManager.STATUS_SUCCESSFUL -> {
                    val sUri = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                    // Update the icon of the item in the ListView.
                    listAdapter.notifyDataSetInvalidated()
                    playSong(Uri.parse(sUri))
                }
                DownloadManager.STATUS_FAILED -> {
                    val reason = c.getString(c.getColumnIndex(DownloadManager.COLUMN_REASON))
                    showErrorDownloadingSong(reason)
                }
            }
            c.close()
        }
    }

    private fun showErrorDownloadingSong(reason: String) {
        Toast.makeText(requireActivity(), getString(R.string.main_fragment_download_failed, reason),
                Toast.LENGTH_SHORT).show()
    }

    private fun downloadSong(song: Song) {
        val request = DownloadManager.Request(Uri.parse(song.url))
        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setAllowedOverRoaming(false)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC,
                song.name + MP3_FILE_EXTENSION)
        // request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, song
        // .getName() + MP3_FILE_EXTENSION);
        request.setTitle(song.name)
        request.setDescription(getString(R.string.main_fragment_description, song.name, song.duration))
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        downloadManager!!.enqueue(request)
    }

    private fun showDownloading(song: Song) {
        Toast.makeText(requireActivity(), getString(R.string.main_fragment_downloading, song.name),
                Toast.LENGTH_SHORT).show()
    }

    private fun playSong(position: Int) {
        lstSongs.setItemChecked(position, true)
        // Update icon of the item in the ListView.
        listAdapter.notifyDataSetInvalidated()
        val song = lstSongs.getItemAtPosition(position) as Song
        val songFile = song.publicFile
        if (songFile != null) {
            playSong(Uri.fromFile(songFile))
        } else {
            downloadSong(song)
            showDownloading(song)
        }
    }

    private fun playSong(uri: Uri) {
        // Update icon of the item in ListView.
        listAdapter.notifyDataSetInvalidated()
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        val file = File(directory, uri.lastPathSegment!!)
        playSongOnService(file)
        showPlaying()
    }

    private fun playSongOnService(file: File) {
        serviceIntent.putExtra(EXTRA_SONG_PATH, file.absolutePath)
        requireActivity().startService(serviceIntent)
    }

    private fun playOrStop() {
        if (lstSongs.checkedItemPosition == AdapterView.INVALID_POSITION) {
            playSong(0)
        } else {
            stopService()
        }
    }

    private fun stopService() {
        stopSongInService()
        // Update item.
        lstSongs.setItemChecked(lstSongs.checkedItemPosition, false)
        showStopped()
    }

    private fun stopSongInService() {
        requireActivity().stopService(serviceIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.fragment_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.mnuDownloads) {
            startActivity(newStandardDownloadsActivityIntent())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupFab() {
        fab = ActivityCompat.requireViewById(requireActivity(), R.id.fab)
        fab.setOnClickListener { _ -> playOrStop() }

    }

    private fun showPlaying() {
        fab.setImageResource(R.drawable.ic_stop_white_24dp)
    }

    private fun showStopped() {
        fab.setImageResource(R.drawable.ic_play_arrow_white_24dp)
    }

    companion object {

        fun newInstance(): MainFragment = MainFragment()

    }

}
