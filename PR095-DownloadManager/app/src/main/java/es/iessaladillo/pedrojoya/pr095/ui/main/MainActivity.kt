package es.iessaladillo.pedrojoya.pr095.ui.main

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import es.iessaladillo.pedrojoya.pr095.R
import es.iessaladillo.pedrojoya.pr095.extensions.newInstalledAppDetailsActivityIntent
import kotlinx.android.synthetic.main.activity_main.*

const val TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT"

class MainActivity : AppCompatActivity() {

    private val quickPermissionsOption by lazy {
        QuickPermissionsOptions(
                rationaleMessage = getString(R.string.main_activity_permission_required_explanation),
                permanentlyDeniedMessage = getString(R.string.main_activity_error_permission_required)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        loadFragment()
    }

    private fun loadFragment() = runWithPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            options = quickPermissionsOption) {
        if (supportFragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            supportFragmentManager.transaction {
                replace(R.id.flContent, MainFragment.newInstance(), TAG_MAIN_FRAGMENT)
            }
            fab.setImageResource(R.drawable.ic_play_arrow_white_24dp)
        }
    }

    private fun initViews() {
        setupToolbar()
        setupFab()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupFab() {
        fab.run {
            setImageResource(R.drawable.ic_settings_white_24dp)
            setOnClickListener { _ -> startActivity(newInstalledAppDetailsActivityIntent(this@MainActivity)) }
        }
    }

}