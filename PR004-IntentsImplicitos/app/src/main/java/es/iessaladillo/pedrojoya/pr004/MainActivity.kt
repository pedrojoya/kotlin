package es.iessaladillo.pedrojoya.pr004

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import androidx.core.widget.toast
import es.iessaladillo.pedrojoya.pr004.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

private const val WEB_URL = "http://www.genbeta.com"
private const val SEARCH_TEXT = "IES Saladillo"
private const val PHONE_NUMBER = "(+34)12345789"
private const val LONGITUDE = 36.1121
private const val LATITUDE = -5.44347
private const val ZOOM = 19
private const val MAP_SEARCH_TEXT = "duque de rivas, Algeciras"
private const val RP_CALL = 1

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnShowInBrowser.setOnClickListener { showInBrowser(WEB_URL) }
        btnSearch.setOnClickListener { search(SEARCH_TEXT) }
        btnCall.setOnClickListener { wantsToCall(PHONE_NUMBER) }
        btnDial.setOnClickListener { dial(PHONE_NUMBER) }
        btnShowInMap.setOnClickListener { showInMap(LONGITUDE, LATITUDE, ZOOM) }
        btnSearchInMap.setOnClickListener { searchInMap(MAP_SEARCH_TEXT) }
        btnShowContacts.setOnClickListener { showContacts() }
    }

    private fun showInBrowser(url: String) {
        startActivityWithCheck(newViewUriIntent(Uri.parse(url))) {
            toast(R.string.main_activity_no_web_browser)
        }
    }

    private fun search(text: String) {
        startActivityWithCheck(newWebSearchIntent(text)) {
            toast(R.string.main_activity_no_web_search)
        }
    }

    private fun wantsToCall(phoneNumber: String) {
        if (!hasPermission(Manifest.permission.CALL_PHONE)) {
            requestCallPermission()
        } else {
            call(phoneNumber)
        }
    }

    private fun requestCallPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),
                RP_CALL)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == RP_CALL && hasPermission(Manifest.permission.CALL_PHONE)) {
            call(PHONE_NUMBER)
        } else {
            // Check if the user set "Don't ask again"
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CALL_PHONE)) {
                reportRationale()
            } else {
                toast(R.string.main_activity_no_call_permission_rationale)
            }
        }
    }

    private fun reportRationale() {
        snackbar(btnCall, R.string.general_permission_required,
                R.string.general_configure) {
            startActivity(newInstalledAppDetailsActivityIntent(this@MainActivity))
        }
    }

    private fun call(phoneNumber: String) {
        startActivityWithCheck(newCallIntent(phoneNumber)) {
            toast(R.string.main_activity_no_call_app)
        }
    }

    private fun dial(phoneNumber: String) {
        startActivityWithCheck(newDialIntent(phoneNumber)) {
            toast(R.string.main_activity_no_dial_app)
        }
    }

    private fun showInMap(longitude: Double, latitude: Double, zoom: Int) {
        startActivityWithCheck(newShowInMapIntent(longitude, latitude, zoom)) {
            toast(R.string.main_activity_no_maps_app)
        }
    }

    private fun searchInMap(text: String) {
        startActivityWithCheck(newSearchInMapIntent(text)) {
            toast(R.string.main_activity_no_maps_app)
        }
    }

    private fun showContacts() {
        startActivityWithCheck(newContactsIntent()) {
            toast(R.string.main_activity_no_contacts_app)
        }
    }

}
