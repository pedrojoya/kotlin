package es.iessaladillo.pedrojoya.pr138

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.TextView
import es.iessaladillo.pedrojoya.pr138.extensions.getThemedColor
import es.iessaladillo.pedrojoya.pr138.extensions.loadUrl
import kotlinx.android.synthetic.main.activity_main.*

private const val PHOTO_BASE_URL = "http://lorempixel.com/400/200/abstract/"

class MainActivity : AppCompatActivity() {

    private var count = 0
    private var palette: Palette? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        loadPhoto()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        imgPhoto.setOnClickListener { loadPhoto() }
        lblVibrant.setOnClickListener { updateToolbar(it as TextView) }
        lblLightVibrant.setOnClickListener { updateToolbar(it as TextView) }
        lblDarkVibrant.setOnClickListener { updateToolbar(it as TextView) }
        lblMuted.setOnClickListener { updateToolbar(it as TextView) }
        lblLightMuted.setOnClickListener { updateToolbar(it as TextView) }
        lblDarkMuted.setOnClickListener { updateToolbar(it as TextView) }
    }

    private fun loadPhoto() {
        imgPhoto.loadUrl(PHOTO_BASE_URL + (count % 10 + 1) + "/", onSuccessAction = {
            obtainPalette()
            count++
        })
    }

    private fun obtainPalette() {
        Palette.from((imgPhoto.drawable as BitmapDrawable).bitmap).generate { palette ->
            this.palette = palette
            showSwatchs(palette)
        }
    }

    private fun showSwatchs(palette: Palette) {
        showSwatch(lblVibrant, palette.vibrantSwatch)
        showSwatch(lblLightVibrant, palette.lightVibrantSwatch)
        showSwatch(lblDarkVibrant, palette.darkVibrantSwatch)
        showSwatch(lblMuted, palette.mutedSwatch)
        showSwatch(lblLightMuted, palette.lightMutedSwatch)
        showSwatch(lblDarkMuted, palette.darkMutedSwatch)
        updateToolbar(lblMuted)
    }

    private fun showSwatch(textView: TextView, swatch: Palette.Swatch?) {
        with(textView) {
            setBackgroundColor(Color.WHITE)
            setTextColor(Color.BLACK)
            swatch?.let {
                setBackgroundColor(it.rgb)
                setTextColor(it.bodyTextColor)
            }
        }
    }

    private fun updateToolbar(textView: TextView) {
        palette?.let {
            toolbar.setBackgroundColor(getBackgroundColor(textView,
                    getThemedColor(R.color.colorPrimary)))
            toolbar.setTitleTextColor(textView.currentTextColor)
            // Default status bar color is the one from the theme.
            window.statusBarColor = when (textView.id) {
                R.id.lblVibrant, R.id.lblLightVibrant, R.id.lblDarkVibrant ->
                    it.getDarkVibrantColor(getThemedColor(R.color.colorPrimaryDark))
                else -> it.getDarkMutedColor(getThemedColor(R.color.colorPrimaryDark))
            }
        }
    }

    // Returns background color of the received view or default one.
    private fun getBackgroundColor(view: View, defaultColor: Int): Int =
        (view.background as? ColorDrawable)?.color ?: defaultColor

}
