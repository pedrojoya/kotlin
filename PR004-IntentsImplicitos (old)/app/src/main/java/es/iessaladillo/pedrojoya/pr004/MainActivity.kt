package es.iessaladillo.pedrojoya.pr004

import android.Manifest
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import es.iessaladillo.pedrojoya.pr004.components.MessageManager
import es.iessaladillo.pedrojoya.pr004.components.ToastMessageManager
import es.iessaladillo.pedrojoya.pr004.utils.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val RP_CALL = 1

    private val WEB_URL = "http://www.genbeta.com"
    private val SEARCH_TEXT = "IES Saladillo"
    private val PHONE_NUMBER = "(+34)12345789"
    private val LONGITUDE = 36.1121
    private val LATITUDE = -5.44347
    private val ZOOM = 19
    private val MAP_SEARCH_TEXT = "duque de rivas, Algeciras"

    private lateinit var messageManager: MessageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messageManager = ToastMessageManager()
        initViews()
    }

    private fun initViews() {
        btnShowInBrowser?.setOnClickListener { showInBrowser(WEB_URL) }
        btnSearch.setOnClickListener { search(SEARCH_TEXT) }
        btnCall.setOnClickListener { wantsToCall(PHONE_NUMBER) }
        btnDial.setOnClickListener { dial(PHONE_NUMBER) }
        btnShowInMap.setOnClickListener { showInMap(LONGITUDE, LATITUDE, ZOOM) }
        btnSearchInMap.setOnClickListener { searchInMap(MAP_SEARCH_TEXT) }
        btnShowContacts.setOnClickListener { showContacts() }
    }

    private fun showInBrowser(url: String) {
        if (isConnectionAvailable(applicationContext)) {
            intent = newViewUriIntent(Uri.parse(url))
            if (isActivityAvailable(applicationContext, intent)) {
                startActivity(intent)
            } else {
                messageManager.showMessage(btnShowInBrowser, getString(R.string.main_activity_no_web_browser))
            }
        } else {
            messageManager.showMessage(btnShowInBrowser, getString(R.string.main_activity_no_connection))
        }
    }

    private fun search(text: String) {
        if (isConnectionAvailable(applicationContext)) {
            intent = newWebSearchIntent(text)
            if (isActivityAvailable(applicationContext, intent)) {
                startActivity(intent)
            } else {
                messageManager.showMessage(btnSearch, getString(R.string.main_activity_no_web_search))
            }
        } else {
            messageManager.showMessage(btnSearch, getString(R.string.main_activity_no_connection))
        }

    }

    private fun  wantsToCall(phoneNumber: String) {
        if (!canCall()) {
            requestCallPermission(RP_CALL)
        } else {
            call(phoneNumber)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == RP_CALL && canCall()) {
            call(PHONE_NUMBER)
        } else {
            // Check if the user set "Don't ask again"
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
                reportRationale()
            } else {
                messageManager.showMessage(btnCall, getString(R.string.main_activity_no_call_permission_rationale))
            }
        }
    }

    private fun reportRationale() {
        Snackbar.make(btnCall, R.string.general_permission_required, Snackbar.LENGTH_LONG).setAction(
                R.string.general_configure
        ) { startInstalledAppDetailsActivity() }.show()
    }

    private fun call(phoneNumber: String) {
        intent = newCallIntent(phoneNumber)
        if (isActivityAvailable(applicationContext, intent)) {
            startActivity(intent)
        } else {
            messageManager.showMessage(btnCall, getString(R.string.main_activity_no_call_app))
        }
    }

    private fun dial(phoneNumber: String) {
        intent = newDialIntent(phoneNumber)
        if (isActivityAvailable(applicationContext, intent)) {
            startActivity(intent)
        } else {
            messageManager.showMessage(btnDial, getString(R.string.main_activity_no_dial_app))
        }
    }

    private fun showInMap(longitude: Double, latitude: Double, zoom: Int) {
        intent = newShowInMapIntent(longitude, latitude, zoom)
        if (isActivityAvailable(applicationContext, intent)) {
            startActivity(intent)
        } else {
            messageManager.showMessage(btnShowInMap, getString(R.string.main_activity_no_maps_app))
        }
    }

    private fun searchInMap(text: String) {
        intent = newSearchInMapIntent(text)
        if (isActivityAvailable(applicationContext, intent)) {
            startActivity(intent)
        } else {
            messageManager.showMessage(btnSearchInMap, getString(R.string.main_activity_no_maps_app))
        }
    }

    private fun showContacts() {
        intent = newContactsIntent()
        if (isActivityAvailable(applicationContext, intent)) {
            startActivity(intent)
        } else {
            messageManager.showMessage(btnCall, getString(R.string.main_activity_no_contacts_app))
        }
    }

}
