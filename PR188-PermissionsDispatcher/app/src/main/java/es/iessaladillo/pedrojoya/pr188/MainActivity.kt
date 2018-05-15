package es.iessaladillo.pedrojoya.pr188

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr188.extensions.newInstalledAppDetailsActivityIntent
import es.iessaladillo.pedrojoya.pr188.extensions.newViewUriIntent
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

private const val RAW_FILE_NAME = "lorem.txt"
private const val ASSET_FILE_NAME = "audio.mp3"
private const val RP_WRITE_EXTERNAL = 1

@RuntimePermissions
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

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        onRequestPermissionsResult(requestCode, grantResults)
    }

    private fun btnDuplicateOnClick() {
        if (hasRequiredPermission()) {
            duplicateFileWithPermissionWithPermissionCheck()
        } else {
            duplicateFile()
        }
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun duplicateFileWithPermission() {
        duplicateFile()
    }

    private fun duplicateFile() {
        try {
            val outputFile = File(getDestinyFolder(), getSourceFilename())
            openInputStream().copyTo(FileOutputStream(outputFile))
            Log.d(getString(R.string.app_name), outputFile.path)
            Snackbar.make(btnDuplicate,
                    getString(R.string.main_activity_file_duplicated, outputFile.path),
                    Snackbar.LENGTH_LONG)
                    .setAction(R.string.main_activity_open) {
                        showFile(outputFile, getSourceMimeType()) }
                    .show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showRationalePermissionDialog(request: PermissionRequest) {
        AlertDialog.Builder(this).setMessage(
                R.string.main_activity_permission_required_explanation).setTitle(
                R.string.main_activity_permission_required).setPositiveButton(android.R.string.ok
        ) { _, _ ->
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    RP_WRITE_EXTERNAL)
        }.show()
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showError() {
        Snackbar.make(btnDuplicate, R.string.main_activity_error_permission_required,
                Snackbar.LENGTH_LONG).show()
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showNotAvailable() {
        Snackbar.make(btnDuplicate, R.string.main_activity_action_permission_required,
                Snackbar.LENGTH_LONG).setAction(R.string.main_activity_configure
        ) { startActivity(newInstalledAppDetailsActivityIntent(this@MainActivity)) }.show()
    }

    private fun hasRequiredPermission() =
            when(rgDestination.checkedRadioButtonId) {
                R.id.rbPersonalExternal, R.id.rbPublicExternal, R.id.rbExternalCache -> true
                else -> false
            }

    private fun showFile(file: File, mimeType: String) {
        // Files stored in personal directories can't be open from external apps.
        try {
            startActivity(newViewUriIntent(FileProvider.getUriForFile(this,
                    "es.iessaladillo.pedrojoya.pr188.provider", file), mimeType).apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            })
        } catch (e: Exception) {
            toast(R.string.main_activity_error_opening_file)
        }
    }

    private fun enableOptions(mounted: Boolean) {
        rbPersonalExternal.isEnabled = mounted
        rbPublicExternal.isEnabled = mounted
        rbExternalCache.isEnabled = mounted
    }

}
