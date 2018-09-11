package es.iessaladillo.pedrojoya.pr123.ui.main

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import es.iessaladillo.pedrojoya.pr123.R
import es.iessaladillo.pedrojoya.pr123.base.ToolbarSpinnerAdapter
import es.iessaladillo.pedrojoya.pr123.extensions.getStringArray
import es.iessaladillo.pedrojoya.pr123.extensions.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

private const val PHOTO_OPTION_POSITION = 0
private const val INFO_OPTION_POSITION = 1
private const val TAG_PHOTO_FRAGMENT = "TAG_PHOTO_FRAGMENT"
private const val TAG_INFO_FRAGMENT = "TAG_INFO_FRAGMENT"

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val viewModel by viewModelProvider<MainActivityViewModel>()
    private val photoFragment: PhotoFragment by lazy {
        supportFragmentManager.findFragmentByTag(TAG_PHOTO_FRAGMENT) as? PhotoFragment
                ?: PhotoFragment.newInstance().apply {
                    supportFragmentManager.transaction {
                        add(R.id.flContent, this@apply, TAG_PHOTO_FRAGMENT)
                    }
                }
    }
    private val infoFragment: InfoFragment by lazy {
        supportFragmentManager.findFragmentByTag(TAG_INFO_FRAGMENT) as? InfoFragment
                ?: InfoFragment.newInstance().apply {
                    supportFragmentManager.transaction {
                        add(R.id.flContent, this@apply, TAG_INFO_FRAGMENT)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        spnOptions.setSelection(viewModel.selectedOption)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        spnOptions.onItemSelectedListener = this
        spnOptions.adapter = ToolbarSpinnerAdapter(
                supportActionBar!!.themedContext,
                arrayListOf(*getStringArray(R.array.options)))
        // toolbar.addView(spnOptions);
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        when (position) {
            PHOTO_OPTION_POSITION -> showPhotoFragment()
            INFO_OPTION_POSITION -> showInfoFragment()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    private fun showPhotoFragment() {
        supportFragmentManager.transaction {
            hide(infoFragment)
            show(photoFragment)
        }
    }

    private fun showInfoFragment() {
        supportFragmentManager.transaction {
            hide(photoFragment)
            show(infoFragment)
        }
    }

}
