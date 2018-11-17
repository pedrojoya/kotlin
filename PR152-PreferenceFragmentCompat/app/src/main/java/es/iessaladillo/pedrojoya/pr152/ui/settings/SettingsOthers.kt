package es.iessaladillo.pedrojoya.pr152.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import es.iessaladillo.pedrojoya.pr152.R

class SettingsOthers : PreferenceFragmentCompat() {

    private val sharedPreferences by lazy { preferenceScreen.sharedPreferences }
    private var onSharedPreferenceChangeListener:
            SharedPreferences.OnSharedPreferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        updateIcon(sharedPreferences, key)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_others, rootKey)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupActionBar()
        // Set icons according to current settings.
        updateIcons()
    }

    private fun setupActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.prefOthers_title)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            setDisplayHomeAsUpEnabled(true)

        }
    }

    override fun onResume() {
        super.onResume()
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    override fun onPause() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(
                onSharedPreferenceChangeListener)
        super.onPause()
    }

    private fun updateIcons() {
        updateIcon(sharedPreferences, getString(R.string.prefNetworkMode_key))
        updateIcon(sharedPreferences, getString(R.string.prefDifficulty_key))
    }

    private fun updateIcon(sharedPreferences: SharedPreferences, key: String) {
        val gaugeResIds = intArrayOf(R.drawable.ic_gauge_empty_black_24dp,
                R.drawable.ic_gauge_low_black_24dp, R.drawable.ic_gauge_high_black_24dp,
                R.drawable.ic_gauge_full_black_24dp)
        val preference = findPreference(key)
        if (preference != null) {
            when (preference.key) {
                getString(R.string.prefNetworkMode_key) ->
                    preference.setIcon(if (sharedPreferences.getBoolean(getString(R.string.prefNetworkMode_key),
                                    resources.getBoolean(R.bool.prefNetworkMode_defaultValue)))
                        R.drawable.ic_signal_wifi_4_bar_black_24dp
                    else
                        R.drawable.ic_signal_wifi_off_black_24dp)
                getString(R.string.prefDifficulty_key) -> {
                    val difficulty = sharedPreferences.getInt(getString(R.string.prefDifficulty_key),
                            resources.getInteger(R.integer.prefDifficulty_defaultValue))
                    // Difficulty 1-4
                    preference.setIcon(gaugeResIds[difficulty - 1])
                }
            }
        }
    }

}
