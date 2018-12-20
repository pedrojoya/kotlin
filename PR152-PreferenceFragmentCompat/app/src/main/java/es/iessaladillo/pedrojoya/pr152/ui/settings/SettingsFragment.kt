package es.iessaladillo.pedrojoya.pr152.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import es.iessaladillo.pedrojoya.pr152.R

class SettingsFragment : PreferenceFragmentCompat() {

    private val sharedPreferences: SharedPreferences by lazy { preferenceScreen.sharedPreferences }
    private var onSharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                this.updateIcon(sharedPreferences, key)
            }
    private var onMultiListChangeListener: SharedPreferences.OnSharedPreferenceChangeListener =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                updateMultiListSummary(sharedPreferences, key)
            }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupActionBar()
        // TODO: It should update summary automatically but it doesn't. Bug in library?
        // setupMultiListSummaryProvider();
        // Initial state
        updateMultiListSummary(preferenceScreen.sharedPreferences, getString(R.string.prefShifts_key))
        updateIcons()
    }

    @Suppress("unused")
    private fun setupMultiListSummaryProvider() {
        EditTextPreference.SimpleSummaryProvider.getInstance()
        val prefShifts: MultiSelectListPreference = findPreference(getString(R.string.prefShifts_key))
        prefShifts.summaryProvider = Preference.SummaryProvider<MultiSelectListPreference> { preference ->
            preference.values.toString()
        }
    }

    private fun setupActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.settings_title)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()
        sharedPreferences.apply {
            registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
            registerOnSharedPreferenceChangeListener(onMultiListChangeListener)
        }
    }

    override fun onPause() {
        sharedPreferences.apply {
            unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
            unregisterOnSharedPreferenceChangeListener(onMultiListChangeListener)
            super.onPause()
        }
    }

    private fun updateIcons() {
        updateIcon(sharedPreferences, getString(R.string.prefSync_key))
        updateIcon(sharedPreferences,
                getString(R.string.prefConnectionType_key))
    }

    private fun updateIcon(sharedPreferences: SharedPreferences, key: String) {
        val preference: Preference? = findPreference(key)
        if (preference != null) {
            when (preference.key) {
                getString(R.string.prefSync_key) -> {
                    val iconResId = if (sharedPreferences.getBoolean(getString(R.string.prefSync_key),
                                    resources.getBoolean(R.bool.prefSync_defaultValue)))
                        R.drawable.ic_sync_black_24dp
                    else
                        R.drawable.ic_sync_disabled_black_24dp
                    preference.setIcon(iconResId)
                }
                getString(R.string.prefConnectionType_key) -> {
                    val iconResId = if (sharedPreferences.getString(getString(R.string
                                    .prefConnectionType_key), getString(R.string
                                    .prefConnectionType_defaultValue)) == "wifi")
                        R.drawable.ic_network_wifi_black_24dp
                    else
                        R.drawable.ic_signal_cellular_3_bar_black_24dp
                    preference.setIcon(iconResId)
                }
            }
        }
    }

    private fun updateMultiListSummary(sharedPreferences: SharedPreferences, key: String) {
        if (key == getString(R.string.prefShifts_key)) {
            val preference = findPreference(
                    getString(R.string.prefShifts_key)) as MultiSelectListPreference
            val summary = if (preference.values.isEmpty()) getString(R.string.prefShifts_summary)
                            else preference.values.toString()
            preference.summary = summary
        }
    }

}
