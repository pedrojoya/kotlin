package es.iessaladillo.pedrojoya.pr123.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import es.iessaladillo.pedrojoya.pr123.R
import es.iessaladillo.pedrojoya.pr123.extensions.addFragment
import es.iessaladillo.pedrojoya.pr123.extensions.findFragmentByTag
import es.iessaladillo.pedrojoya.pr123.extensions.getStringArray
import es.iessaladillo.pedrojoya.pr123.extensions.inTransaction
import es.iessaladillo.pedrojoya.pr123.utils.ToolbarSpinnerAdapter
import kotlinx.android.synthetic.main.activity_main.*

private const val PHOTO_OPTION_POSITION = 0
private const val INFO_OPTION_POSITION = 1
private const val TAG_PHOTO_FRAGMENT = "TAG_PHOTO_FRAGMENT"
private const val TAG_INFO_FRAGMENT = "TAG_INFO_FRAGMENT"
private const val STATE_SELECTED_OPTION = "STATE_SELECTED_OPTION"

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var selectedOption = 0
    private var photoFragment: PhotoFragment? = null
    private var infoFragment: InfoFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreInstanceState(savedInstanceState)
        initViews()
        loadFragments()
    }

    private fun loadFragments() {
        photoFragment = findFragmentByTag(TAG_PHOTO_FRAGMENT) as? PhotoFragment
        if (photoFragment == null) {
            photoFragment = PhotoFragment.newInstance()
            addFragment(R.id.flContent, photoFragment!!, TAG_PHOTO_FRAGMENT)
        }
        infoFragment = findFragmentByTag(TAG_INFO_FRAGMENT) as? InfoFragment
        if (infoFragment == null) {
            infoFragment = InfoFragment.newInstance()
            addFragment(R.id.flContent, infoFragment!!, TAG_INFO_FRAGMENT)
        }
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        selectedOption = savedInstanceState?.getInt(STATE_SELECTED_OPTION, 0) ?: 0
    }

    private fun initViews() {
        setupToolbar()
        spnOptions.setSelection(selectedOption)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        spnOptions.onItemSelectedListener = this
        spnOptions.adapter = ToolbarSpinnerAdapter(
                supportActionBar!!.themedContext,
                arrayListOf(*getStringArray(R.array.options)))
        // toolbar.addView(spnOptions);
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SELECTED_OPTION, spnOptions!!.selectedItemPosition)
        super.onSaveInstanceState(outState)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        when (position) {
            PHOTO_OPTION_POSITION -> showPhotoFragment()
            INFO_OPTION_POSITION -> showInfoFragment()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    private fun showPhotoFragment() {
        inTransaction {
            hide(infoFragment)
            show(photoFragment)
        }
    }

    private fun showInfoFragment() {
        inTransaction {
            hide(photoFragment)
            show(infoFragment)
        }
    }

}
