package es.iessaladillo.pedrojoya.pr119.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr119.R
import es.iessaladillo.pedrojoya.pr119.extensions.hasPermission
import es.iessaladillo.pedrojoya.pr119.extensions.newInstalledAppDetailsActivityIntent
import es.iessaladillo.pedrojoya.pr119.extensions.newViewUriIntent
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

private const val RAW_FILE_NAME = "lorem.txt"
private const val ASSET_FILE_NAME = "audio.mp3"
private const val RP_WRITE_EXTERNAL = 1

class MainFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
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
                    getString(R.string.main_file_duplicated, outputFile.path),
                    Snackbar.LENGTH_LONG).setAction(R.string.main_open
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
            startActivity(newViewUriIntent(FileProvider.getUriForFile(requireContext(),
                    "es.iessaladillo.pedrojoya.pr119.provider", file), mimeType).apply {
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            })
        } catch (e: Exception) {
            Toast.makeText(requireContext(), R.string.main_error_opening_file, Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showRationalePermissionDialog()
        } else {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), RP_WRITE_EXTERNAL)
        }
    }

    private fun showRationalePermissionDialog() {
        AlertDialog.Builder(requireContext()).setMessage(
                R.string.main_permission_required_explanation).setTitle(
                R.string.main_permission_required).setPositiveButton(android.R.string.ok
        ) { _, i ->
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    RP_WRITE_EXTERNAL)
        }.show()
    }

    private fun getSubfolderType(): String =
            if (rgSource.checkedRadioButtonId == R.id.rbRaw) Environment.DIRECTORY_DOWNLOADS
            else Environment.DIRECTORY_MUSIC

    private fun getDestinyFolder(): File = when (rgDestination.checkedRadioButtonId) {
        R.id.rbPersonalExternal -> requireContext().getExternalFilesDir(getSubfolderType())!!
        R.id.rbPublicExternal -> Environment.getExternalStoragePublicDirectory(getSubfolderType())
        R.id.rbInternalCache -> requireContext().cacheDir
        R.id.rbExternalCache -> requireContext().externalCacheDir!!
        else -> requireContext().filesDir
    }

    private fun openInputStream(): InputStream =
            if (rgSource.checkedRadioButtonId == R.id.rbRaw) resources.openRawResource(R.raw.lorem)
            else requireContext().assets.open(getSourceFilename())

    private fun getSourceFilename(): String =
            if (rgSource.checkedRadioButtonId == R.id.rbRaw) RAW_FILE_NAME
            else ASSET_FILE_NAME

    private fun getSourceMimeType(): String =
            if (rgSource.checkedRadioButtonId == R.id.rbRaw) "text/plain" else "audio/mp3"


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RP_WRITE_EXTERNAL) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                duplicateFile()
            } else {
                // "Don't ask again" checked?
                // Ask.
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Not checked.
                    Snackbar.make(btnDuplicate, R.string.main_error_permission_required, Snackbar.LENGTH_LONG).show()
                } else {
                    // Checked.
                    Snackbar.make(btnDuplicate, R.string.main_action_permission_required, Snackbar.LENGTH_LONG)
                            .setAction(R.string.main_configure) {
                                startActivity(newInstalledAppDetailsActivityIntent(requireContext()))
                            }
                            .show()
                }
            }
        }
    }

    companion object {

        fun newInstance() = MainFragment()

    }
}