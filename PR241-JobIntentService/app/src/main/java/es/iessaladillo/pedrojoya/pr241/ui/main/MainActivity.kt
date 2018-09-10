package es.iessaladillo.pedrojoya.pr241.ui.main

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import es.iessaladillo.pedrojoya.pr100.extensions.setOnSwipeRightListener
import es.iessaladillo.pedrojoya.pr241.BuildConfig
import es.iessaladillo.pedrojoya.pr241.R
import es.iessaladillo.pedrojoya.pr241.services.ACTION_EXPORTED
import es.iessaladillo.pedrojoya.pr241.services.EXTRA_FILENAME
import es.iessaladillo.pedrojoya.pr241.services.ExportToTextFileService
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var broadcastReceiver: BroadcastReceiver
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var listAdapter: MainActivityAdapter
    private lateinit var quickPermissionsOption: QuickPermissionsOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        quickPermissionsOption = QuickPermissionsOptions(
                rationaleMessage = getString(R.string.main_activity_permission_required_explanation),
                permanentlyDeniedMessage = getString(R.string.main_activity_error_permission_required)
        )
        initViews()
        setupReceiver()
    }

    private fun initViews() {
        setupToolbar()
        setupFAB()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupFAB() {
        btnExport.setOnClickListener { _ -> export() }
    }

    private fun setupRecyclerView() {
        listAdapter = MainActivityAdapter(viewModel.students)
        listAdapter.emptyView = emptyView
        lstStudents.run {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            setOnSwipeRightListener { viewHolder ->  deleteStudent(viewHolder.adapterPosition) }
        }
    }

    private fun deleteStudent(position: Int) {
        viewModel.deleteStudent(position)
        listAdapter.notifyItemRemoved(position)
    }

    private fun setupReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                val uri: Uri = intent.getParcelableExtra(EXTRA_FILENAME)
                showSnackBar(uri)
            }
        }
    }

    public override fun onResume() {
        super.onResume()
        val exportarFilter = IntentFilter(ACTION_EXPORTED)
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, exportarFilter)
    }

    public override fun onPause() {
        super.onPause()
        // Se quita del registro el receptor.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    private fun showSnackBar(uri: Uri) {
        Snackbar.make(lstStudents!!, R.string.main_activity_exported, Snackbar.LENGTH_LONG).setAction(
                R.string.main_activity_open) { _ -> showFile(uri) }.show()
    }

    private fun showFile(uri: Uri) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(FileProvider.getUriForFile(this@MainActivity,
                                BuildConfig.APPLICATION_ID + ".fileprovider",
                                File(uri.path!!)), "text/plain")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun export() = runWithPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, options = quickPermissionsOption) {
        ExportToTextFileService.start(this, viewModel.students)
    }

}
