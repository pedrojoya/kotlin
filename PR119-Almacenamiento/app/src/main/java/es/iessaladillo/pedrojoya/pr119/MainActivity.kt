package es.iessaladillo.pedrojoya.pr119

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr119.extensions.hasPermission
import es.iessaladillo.pedrojoya.pr119.extensions.newInstalledAppDetailsActivityIntent
import es.iessaladillo.pedrojoya.pr119.extensions.newViewUriIntent
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

private const val RAW_FILE_NAME = "lorem.txt"
private const val ASSET_FILE_NAME = "audio.mp3"
private const val RP_WRITE_EXTERNAL = 1

class MainActivity : AppCompatActivity() {

    private fun getSubfolderType(): String =
            if (rgSource.checkedRadioButtonId == R.id.rbRaw) Environment.DIRECTORY_DOWNLOADS
            else Environment.DIRECTORY_MUSIC

    private fun getDestinyFolder(): File = when (rgDestination.checkedRadioButtonId) {
        R.id.rbPersonalExternal -> getExternalFilesDir(getSubfolderType())
        R.id.rbPublicExternal -> Environment.getExternalStoragePublicDirectory(getSubfolderType())
        R.id.rbInternalCache -> cacheDir
        R.id.rbExternalCache -> externalCacheDir
        else -> filesDir
    }

    private fun openInputStream(): InputStream =
            if (rgSource.checkedRadioButtonId == R.id.rbRaw) resources.openRawResource(R.raw.lorem)
            else assets.open(getSourceFilename())

    private fun getSourceFilename(): String =
            if (rgSource.checkedRadioButtonId == R.id.rbRaw) RAW_FILE_NAME
            else ASSET_FILE_NAME

    private fun getSourceMimeType(): String =
            if (rgSource.checkedRadioButtonId == R.id.rbRaw) "text/plain" else "audio/mp3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnDuplicate.setOnClickListener { btnDuplicateOnClick() }
        enableOptions(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)

    }

    private fun btnDuplicateOnClick() {
        if (isPermissionRequired() && !hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermission()
        } else {
            duplicateFile()
        }
    }

    private fun duplicateFile() {
        try {
            val outputFile = File(getDestinyFolder(), getSourceFilename())
            openInputStream().copyTo(FileOutputStream(outputFile))
            Log.d(getString(R.string.app_name), outputFile.path)
            Snackbar.make(btnDuplicate,
                    getString(R.string.main_activity_file_duplicated, outputFile.path),
                    Snackbar.LENGTH_LONG).setAction(R.string.main_activity_open
            ) { showFile(outputFile, getSourceMimeType()) }.show()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun enableOptions(mounted: Boolean) {
        rbPersonalExternal.isEnabled = mounted
        rbPublicExternal.isEnabled = mounted
        rbExternalCache.isEnabled = mounted
    }

    private fun isPermissionRequired() =
            when(rgDestination.checkedRadioButtonId) {
                R.id.rbPersonalExternal, R.id.rbPublicExternal, R.id.rbExternalCache -> true
                else -> false
            }

    private fun showFile(file: File, mimeType: String) {
        // Files stored in personal directories can't be open from external apps.
        try {
            startActivity(newViewUriIntent(FileProvider.getUriForFile(this,
                    "es.iessaladillo.pedrojoya.pr119.provider", file), mimeType).apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            })
        } catch (e: Exception) {
            toast(R.string.main_activity_error_opening_file)
        }
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showRationalePermissionDialog()
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), RP_WRITE_EXTERNAL)
        }
    }

    private fun showRationalePermissionDialog() {
        AlertDialog.Builder(this).setMessage(
                R.string.main_activity_permission_required_explanation).setTitle(
                R.string.main_activity_permission_required).setPositiveButton(android.R.string.ok
        ) { _, i ->
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    RP_WRITE_EXTERNAL)
        }.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == RP_WRITE_EXTERNAL) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                duplicateFile()
            } else {
                // "Don't ask again" checked?
                // Ask.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Not checked.
                    Snackbar.make(btnDuplicate, R.string.main_activity_error_permission_required, Snackbar.LENGTH_LONG).show()
                } else {
                    // Checked.
                    Snackbar.make(btnDuplicate, R.string.main_activity_action_permission_required, Snackbar.LENGTH_LONG)
                            .setAction(R.string.main_activity_configure) {
                                startActivity(newInstalledAppDetailsActivityIntent(this@MainActivity))
                            }
                            .show()
                }
            }
        }
    }

}
