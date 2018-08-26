package es.iessaladillo.pedrojoya.pr152.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.MultiSelectListPreference
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.*
import androidx.core.os.bundleOf
import es.iessaladillo.pedrojoya.pr152.R
import es.iessaladillo.pedrojoya.pr152.extensions.getBoolean

class SettingsFragment : PreferenceFragmentCompat(),
        SharedPreferences.OnSharedPreferenceChangeListener {
    private var preferenceScreenKey: String? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceScreenKey = rootKey
        setPreferencesFromResource(R.xml.settings, rootKey)
        for (i in 0 until preferenceScreen.preferenceCount) {
            initSummary(preferenceScreen.getPreference(i))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
                if (preferenceScreenKey == null)
                    getString(R.string.activity_settings_title)
                else
                    findPreference(preferenceScreenKey).title
    }

    private fun initSummary(preference: Preference) {
        when (preference) {
            is PreferenceScreen ->
                for (i in 0 until preference.preferenceCount) {
                    initSummary(preference.getPreference(i))
                }
            is PreferenceCategory ->
                for (i in 0 until preference.preferenceCount) {
                    initSummary(preference.getPreference(i))
                }
            else -> {
                updateSummary(preference)
                updateIcon(preference, preference.sharedPreferences)
            }
        }
    }

    override fun onResume() {
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        super.onResume()
    }

    override fun onPause() {
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(
                this)
        super.onPause()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val preference = findPreference(key)
        updateSummary(preference)
        updateIcon(preference, sharedPreferences!!)
    }

    private fun updateSummary(preference: Preference) {
        when (preference) {
            is EditTextPreference -> preference.summary = preference.text
            is ListPreference -> preference.summary = preference.entry
            is MultiSelectListPreference -> preference.summary = preference.values.toString()
            is SeekBarPreference -> preference.summary = preference.value.toString()
        }
    }

    private fun updateIcon(preference: Preference, sharedPreferences: SharedPreferences) {
        when (preference.key) {
            getString(R.string.prefSync_key) ->
                preference.setIcon(
                        if (sharedPreferences.getBoolean(getString(R.string.prefSync_key),
                                getBoolean(R.bool.prefSync_defaultValue)))
                            R.drawable.ic_sync_black_24dp
                        else
                            R.drawable.ic_sync_disabled_black_24dp
                )
            getString(R.string.prefConnectionType_key) ->
                preference.setIcon(
                        if (sharedPreferences.getString(getString(R.string.prefConnectionType_key),
                                        getString(R.string.prefConnectionType_defaultValue)) == "wifi")
                            R.drawable.ic_network_wifi_black_24dp
                        else
                            R.drawable.ic_signal_cellular_3_bar_black_24dp
                )
            getString(R.string.prefNetworkMode_key) ->
                preference.setIcon(
                        if (sharedPreferences.getBoolean(getString(R.string.prefNetworkMode_key),
                                getBoolean(R.bool.prefNetworkMode_defaultValue)))
                            R.drawable.ic_signal_wifi_4_bar_black_24dp
                        else
                            R.drawable.ic_signal_wifi_off_black_24dp)
        }
    }

    companion object {

        fun newInstance(preferenceScreenKey: String?): SettingsFragment =
                SettingsFragment().apply {
                    arguments = bundleOf(
                            PreferenceFragmentCompat.ARG_PREFERENCE_ROOT to preferenceScreenKey)
                }

    }

}
