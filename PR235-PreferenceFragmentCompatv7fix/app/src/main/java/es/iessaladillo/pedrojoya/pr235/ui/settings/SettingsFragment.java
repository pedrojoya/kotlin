package es.iessaladillo.pedrojoya.pr235.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.takisoft.preferencex.EditTextPreference;
import com.takisoft.preferencex.PreferenceCategory;
import com.takisoft.preferencex.PreferenceFragmentCompat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SeekBarPreference;
import es.iessaladillo.pedrojoya.pr235.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private String preferenceScreenKey;

    public static SettingsFragment newInstance(String preferenceScreenKey) {
        SettingsFragment frg = new SettingsFragment();
        Bundle argumentos = new Bundle();
        argumentos.putString(ARG_PREFERENCE_ROOT, preferenceScreenKey);
        frg.setArguments(argumentos);
        return frg;
    }

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        preferenceScreenKey = rootKey;
        setPreferencesFromResource(R.xml.settings, rootKey);
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            initSummary(getPreferenceScreen().getPreference(i));
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
    }

    private void setupToolbar() {
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle((preferenceScreenKey == null) ? getString(
                    R.string.activity_settings_title) : findPreference(
                    preferenceScreenKey).getTitle());
        }
    }

    private void initSummary(Preference preference) {
        if (preference instanceof PreferenceScreen) {
            PreferenceScreen preferenceScreen = (PreferenceScreen) preference;
            for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
                initSummary(preferenceScreen.getPreference(i));
            }
        } else if (preference instanceof PreferenceCategory) {
            PreferenceCategory preferenceCategory = (PreferenceCategory) preference;
            for (int i = 0; i < preferenceCategory.getPreferenceCount(); i++) {
                initSummary(preferenceCategory.getPreference(i));
            }
        } else {
            updateSummary(preference);
            updateIcon(preference, preference.getSharedPreferences());
        }

    }

    @Override
    public void onResume() {
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(
                this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        updateSummary(preference);
        updateIcon(preference, sharedPreferences);
    }

    private void updateSummary(Preference preference) {
        if (preference instanceof EditTextPreference) {
            EditTextPreference pref = (EditTextPreference) preference;
            pref.setSummary(pref.getText());
        } else if (preference instanceof ListPreference) {
            ListPreference pref = (ListPreference) preference;
            pref.setSummary(pref.getEntry());
        } else if (preference instanceof MultiSelectListPreference) {
            MultiSelectListPreference pref = (MultiSelectListPreference) preference;
            pref.setSummary(pref.getValues().toString());
        } else if (preference instanceof SeekBarPreference) {
            SeekBarPreference pref = (SeekBarPreference) preference;
            pref.setSummary(String.valueOf(pref.getValue()));
        }
    }

    private void updateIcon(Preference preference, SharedPreferences sharedPreferences) {
        if (TextUtils.equals(preference.getKey(), getString(R.string.prefSync_key))) {
            preference.setIcon(sharedPreferences.getBoolean(getString(R.string.prefSync_key),
                    getResources().getBoolean(
                            R.bool.prefSync_defaultValue)) ? R.drawable.ic_sync_black_24dp : R.drawable.ic_sync_disabled_black_24dp);
        } else if (TextUtils.equals(preference.getKey(),
                getString(R.string.prefConnectionType_key))) {
            preference.setIcon(TextUtils.equals(
                    sharedPreferences.getString(getString(R.string.prefConnectionType_key),
                            getString(R.string.prefConnectionType_defaultValue)),
                    "wifi") ? R.drawable.ic_network_wifi_black_24dp : R.drawable.ic_signal_cellular_3_bar_black_24dp);
        } else if (TextUtils.equals(preference.getKey(), getString(R.string.prefNetworkMode_key))) {
            preference.setIcon(sharedPreferences.getBoolean(getString(R.string.prefNetworkMode_key),
                    getResources().getBoolean(
                            R.bool.prefNetworkMode_defaultValue)) ? R.drawable.ic_signal_wifi_4_bar_black_24dp : R.drawable.ic_signal_wifi_off_black_24dp);
        }
    }

}
