package es.iessaladillo.pedrojoya.pr152.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import es.iessaladillo.pedrojoya.pr152.R
import es.iessaladillo.pedrojoya.pr152.extensions.extraString
import kotlinx.android.synthetic.main.activity_settings.*

private const val EXTRA_PREFERENCE_SCREEN_KEY = "EXTRA_PREFERENCE_SCREEN_KEY"
private const val TAG_PREFERENCE_FRAGMENT = "TAG_PREFERENCE_FRAGMENT"

class SettingsActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    private val preferenceScreenKey: String? by extraString(EXTRA_PREFERENCE_SCREEN_KEY, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initViews()
        showPreferenceFragment()
    }

    private fun initViews() {
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun showPreferenceFragment() {
        if (supportFragmentManager.findFragmentByTag(TAG_PREFERENCE_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flContent, SettingsFragment.newInstance(preferenceScreenKey))
            }
        }
    }

    override fun onPreferenceStartScreen(preferenceFragmentCompat: PreferenceFragmentCompat,
                                         preferenceScreen: PreferenceScreen): Boolean {
        SettingsActivity.start(this, preferenceScreen.key)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {

        fun start(context: Context, preferenceScreenKey: String?) {
            context.startActivity(
                    Intent(context, SettingsActivity::class.java).apply {
                        putExtra(EXTRA_PREFERENCE_SCREEN_KEY, preferenceScreenKey)
                    })
        }

    }

}
