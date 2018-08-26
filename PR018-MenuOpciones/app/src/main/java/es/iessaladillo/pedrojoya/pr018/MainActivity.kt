package es.iessaladillo.pedrojoya.pr018

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.pr018.utils.BINARY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.GREY_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.INVERTED_COLOR_FILTER
import es.iessaladillo.pedrojoya.pr018.utils.SEPIA_COLOR_FILTER
import kotlinx.android.synthetic.main.activity_main.*

private const val STATE_EFFECT = "STATE_EFFECT"
private const val STATE_VISIBLE = "STATE_VISIBLE"

class MainActivity : AppCompatActivity() {

    @IdRes
    private var effectId = R.id.mnuOriginal
    private var isPhotoVisible = true

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreSavedInstanceState(savedInstanceState)
        initViews()

    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            effectId = savedInstanceState.getInt(STATE_EFFECT)
            isPhotoVisible = savedInstanceState.getBoolean(STATE_VISIBLE)
        }
    }

    private fun initViews() {
        imgPhoto.visibility = if (isPhotoVisible) View.VISIBLE else View.INVISIBLE
        setCorrectBitmap(effectId)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_EFFECT, effectId)
        outState.putBoolean(STATE_VISIBLE, isPhotoVisible)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        menu.findItem(effectId).isChecked = true
        menu.findItem(R.id.mnuVisible).isChecked = isPhotoVisible
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
                    Toast.makeText(this, R.string.main_activity_info, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.mnuVisible -> {
                    isPhotoVisible = !isPhotoVisible
                    item.isChecked = isPhotoVisible
                    imgPhoto.visibility = if (item.isChecked) View.VISIBLE else View.INVISIBLE
                    true
                }
                R.id.mnuEdit -> {
                    Toast.makeText(this, R.string.main_activity_edit, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.mnuDelete -> {
                    Toast.makeText(this, R.string.main_activity_delete, Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.mnuOriginal, R.id.mnuGrey, R.id.mnuSepia, R.id.mnuBinary, R.id.mnuInverted -> {
                    effectId = item.itemId
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