package es.iessaladillo.pedrojoya.pr018.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr018.R
import es.iessaladillo.pedrojoya.pr018.extensions.toast
import es.iessaladillo.pedrojoya.pr018.extensions.viewModelProvider
import es.iessaladillo.pedrojoya.pr018.utils.BINARY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.GREY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.INVERTED_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.SEPIA_COLOR_FILTER
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModelProvider()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

    }

    private fun initViews() {
        imgPhoto.visibility = if (viewModel.isPhotoVisible) View.VISIBLE else View.INVISIBLE
        setCorrectBitmap(viewModel.effectId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        menu.findItem(viewModel.effectId).isChecked = true
        menu.findItem(R.id.mnuVisible).isChecked = viewModel.isPhotoVisible
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (menu.findItem(R.id.mnuInverted).isChecked) {
            menu.findItem(R.id.mnuActions).isEnabled = false
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.mnuInfo -> {
                    toast(R.string.main_activity_info)
                    true
                }
                R.id.mnuVisible -> {
                    viewModel.isPhotoVisible = !viewModel.isPhotoVisible
                    item.isChecked = viewModel.isPhotoVisible
                    imgPhoto.visibility = if (item.isChecked) View.VISIBLE else View.INVISIBLE
                    true
                }
                R.id.mnuEdit -> {
                    toast(R.string.main_activity_edit)
                    true
                }
                R.id.mnuDelete -> {
                    toast(R.string.main_activity_delete)
                    true
                }
                R.id.mnuOriginal, R.id.mnuGrey, R.id.mnuSepia, R.id.mnuBinary, R.id.mnuInverted -> {
                    viewModel.effectId = item.itemId
                    item.isChecked = true
                    setCorrectBitmap(item.itemId)
                    invalidateOptionsMenu()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }


    private fun setCorrectBitmap(@IdRes itemId: Int) {
        imgPhoto.drawable.colorFilter = when (itemId) {
            R.id.mnuGrey -> GREY_COLOR_FILTER
            R.id.mnuSepia -> SEPIA_COLOR_FILTER
            R.id.mnuBinary -> BINARY_COLOR_FILTER
            R.id.mnuInverted -> INVERTED_COLOR_FILTER
            else -> null
        }
    }

}