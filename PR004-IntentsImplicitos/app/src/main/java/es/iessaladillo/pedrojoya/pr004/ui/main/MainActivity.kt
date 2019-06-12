package es.iessaladillo.pedrojoya.pr004.ui.main

import android.content.ActivityNotFoundException
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr004.R
import es.iessaladillo.pedrojoya.pr004.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

private const val WEB_URL = "http://www.genbeta.com"
private const val SEARCH_TEXT = "IES Saladillo"
private const val PHONE_NUMBER = "(+34)12345789"
private const val LONGITUDE = 36.1121
private const val LATITUDE = -5.44347
private const val ZOOM = 19
private const val MAP_SEARCH_TEXT = "duque de rivas, Algeciras"

class MainActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        btnShowInBrowser.setOnClickListener { showInBrowser(WEB_URL) }
        btnSearch.setOnClickListener { search(SEARCH_TEXT) }
        btnDial.setOnClickListener { dial(PHONE_NUMBER) }
        btnShowInMap.setOnClickListener { showInMap(LONGITUDE, LATITUDE, ZOOM) }
        btnSearchInMap.setOnClickListener { searchInMap(MAP_SEARCH_TEXT) }
        btnShowContacts.setOnClickListener { showContacts() }
    }

    private fun showInBrowser(url: String) {
        val intent = newViewUriIntent(Uri.parse(url))
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            toast(R.string.main_no_web_browser)
        }
    }

    private fun search(text: String) {
        val intent = newWebSearchIntent(text)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            toast(R.string.main_no_web_search)
        }
    }

    private fun dial(phoneNumber: String) {
        val intent = newDialIntent(phoneNumber)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            toast(R.string.main_no_dial_app)
        }
    }

    private fun showInMap(longitude: Double, latitude: Double, zoom: Int) {
        val intent = newShowInMapIntent(longitude, latitude, zoom)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            toast(R.string.main_no_maps_app)
        }
    }

    private fun searchInMap(text: String) {
        val intent = newSearchInMapIntent(text)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            toast(R.string.main_no_maps_app)
        }
    }

    private fun showContacts() {
        val intent = newContactsIntent()
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            toast(R.string.main_no_contacts_app)
        }
    }

}
