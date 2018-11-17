package es.iessaladillo.pedrojoya.pr152.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.ArraySet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import es.iessaladillo.pedrojoya.pr152.R
import es.iessaladillo.pedrojoya.pr152.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class MainFragment : Fragment() {

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }
    private val onSharePreferencesChangeListener: SharedPreferences.OnSharedPreferenceChangeListener =
            SharedPreferences.OnSharedPreferenceChangeListener { _, _ -> showSettings() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, parent, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupActionBar()
        // Initial state.
        showSettings()
    }

    private fun setupActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onPause() {
        settings.unregisterOnSharedPreferenceChangeListener(onSharePreferencesChangeListener)
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        settings.registerOnSharedPreferenceChangeListener(onSharePreferencesChangeListener)
    }

    private fun showSettings() {
        val sb = StringBuilder()
        sb.append(getString(R.string.prefSync_key)).append(": ").append(
                settings.getBoolean(getString(R.string.prefSync_key),
                        resources.getBoolean(R.bool.prefSync_defaultValue))).append("\n")
        sb.append(getString(R.string.prefConnectionType_key)).append(": ").append(
                settings.getString(getString(R.string.prefConnectionType_key),
                        getString(R.string.prefConnectionType_defaultValue))).append("\n")
        sb.append(getString(R.string.prefLanguage_key)).append(": ").append(
                settings.getString(getString(R.string.prefLanguage_key),
                        getString(R.string.prefLanguage_defaultValue))).append("\n")
        sb.append(getString(R.string.prefBigFontSize_key)).append(": ").append(
                settings.getBoolean(getString(R.string.prefBigFontSize_key),
                        resources.getBoolean(R.bool.prefBigFontSize_defaultValue))).append("\n")
        sb.append(getString(R.string.prefShifts_key)).append(":\n")
        val defaultShifts = ArraySet(
                Arrays.asList(*resources.getStringArray(R.array.prefShifts_defaultValue)))
        val selectedShifts = settings.getStringSet(getString(R.string.prefShifts_key),
                defaultShifts)
        if (selectedShifts != null) {
            val shifts = selectedShifts.toTypedArray()
            for (shift in shifts) {
                sb.append(shift).append("\n")
            }
        }
        sb.append(getString(R.string.prefCatchPhrase_key)).append(": ").append(
                settings.getString(getString(R.string.prefCatchPhrase_key), "")).append("\n")
        sb.append(getString(R.string.prefNetworkMode_key)).append(": ").append(
                settings.getBoolean(getString(R.string.prefNetworkMode_key),
                        resources.getBoolean(R.bool.prefNetworkMode_defaultValue))).append("\n")
        sb.append(getString(R.string.prefDifficulty_key)).append(": ").append(settings.getInt(getString(R.string.prefDifficulty_key),
                resources.getInteger(R.integer.prefDifficulty_defaultValue)).toString()).append("\n")
        lblSettings.text = sb.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuSettings -> {
                showSettingsFragment()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSettingsFragment() {
        requireFragmentManager().commit {
            replace(R.id.flContent,
                    SettingsFragment(), SettingsFragment::class.java.simpleName)
            addToBackStack(SettingsFragment::class.java.simpleName)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

}
