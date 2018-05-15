package es.iessaladillo.pedrojoya.pr152.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import es.iessaladillo.pedrojoya.pr152.R
import es.iessaladillo.pedrojoya.pr152.extensions.getBoolean
import es.iessaladillo.pedrojoya.pr152.extensions.getInteger
import es.iessaladillo.pedrojoya.pr152.extensions.getStringArray
import es.iessaladillo.pedrojoya.pr152.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        settings = PreferenceManager.getDefaultSharedPreferences(this)
        settings.registerOnSharedPreferenceChangeListener(this)
        showSettings()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { showSettingsActivity() }
    }

    private fun showSettingsActivity() {
        // No preferenceScreenKey initially.
        SettingsActivity.start(this, null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.mnuPreferencias -> {
                    showSettingsActivity(); true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onDestroy() {
        settings.unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        showSettings()
    }

    private fun showSettings() {
        val sb = StringBuilder()
                .append(getString(R.string.prefSync_key)).append(": ")
                .append(settings.getBoolean(getString(R.string.prefSync_key),
                        getBoolean(R.bool.prefSync_defaultValue)))
                .append("\n")

                .append(getString(R.string.prefConnectionType_key)).append(": ")
                .append(settings.getString(getString(R.string.prefConnectionType_key),
                        getString(R.string.prefConnectionType_defaultValue))).append("\n")

                .append(getString(R.string.prefLanguage_key)).append(": ")
                .append(settings.getString(getString(R.string.prefLanguage_key),
                        getString(R.string.prefLanguage_defaultValue))).append("\n")

                .append(getString(R.string.prefBigFontSize_key)).append(": ")
                .append(settings.getBoolean(getString(R.string.prefBigFontSize_key),
                        getBoolean(R.bool.prefBigFontSize_defaultValue))).append("\n")

                .append(getString(R.string.prefShifts_key)).append(":\n")
                .append(buildSelectedShiftsString()).append("\n")

                .append(getString(R.string.prefCatchPhrase_key)).append(": ")
                .append(settings.getString(getString(R.string.prefCatchPhrase_key), ""))
                .append("\n")

                .append(getString(R.string.prefNetworkMode_key)).append(": ")
                .append(settings.getBoolean(getString(R.string.prefNetworkMode_key),
                        getBoolean(R.bool.prefNetworkMode_defaultValue))).append("\n")

                .append(getString(R.string.prefDifficulty_key)).append(": ")
                .append(settings.getInt(getString(R.string.prefDifficulty_key),
                        getInteger(R.integer.prefDifficulty_defaultValue)).toString())
                .append("\n")
        lblSettings.text = sb.toString()
    }

    private fun buildSelectedShiftsString(): String =
            settings.getStringSet(getString(R.string.prefShifts_key),
                mutableSetOf(*getStringArray(R.array.prefShifts_defaultValue)))
                    .joinToString(", ")

}
