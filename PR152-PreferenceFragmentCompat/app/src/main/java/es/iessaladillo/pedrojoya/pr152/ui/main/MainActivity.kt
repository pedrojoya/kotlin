package es.iessaladillo.pedrojoya.pr152.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr152.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        // Load initial fragment
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.flContent,
                        MainFragment(), MainFragment::class.java.simpleName)
            }
        }
    }

    private fun setupToolbar() {
        val toolbar = ActivityCompat.requireViewById<Toolbar>(this, R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}



/*class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
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
                    ?.joinToString(", ")?:""

}*/


