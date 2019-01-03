package es.iessaladillo.pedrojoya.pr138.ui.main

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import es.iessaladillo.pedrojoya.pr138.R
import es.iessaladillo.pedrojoya.pr138.extensions.loadUrl
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

private const val PHOTO_BASE_URL = "https://picsum.photos/400/200?image="

class MainFragment : Fragment() {

    private var palette: Palette? = null
    private val random = Random()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        loadPhoto()
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        imgPhoto.setOnClickListener { loadPhoto() }
        lblVibrant.setOnClickListener { updateToolbar(it as TextView) }
        lblLightVibrant.setOnClickListener { updateToolbar(it as TextView) }
        lblDarkVibrant.setOnClickListener { updateToolbar(it as TextView) }
        lblMuted.setOnClickListener { updateToolbar(it as TextView) }
        lblLightMuted.setOnClickListener { updateToolbar(it as TextView) }
        lblDarkMuted.setOnClickListener { updateToolbar(it as TextView) }
    }

    private fun loadPhoto() {
        imgPhoto.loadUrl("$PHOTO_BASE_URL${random.nextInt(1084)}") {
            obtainPalette()
        }
    }

    private fun obtainPalette() {
        Palette.from((imgPhoto.drawable as BitmapDrawable).bitmap).generate { palette ->
            this.palette = palette
            if (palette != null) {
                showSwatchs(palette)
            }
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
        textView.run {
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
                    ContextCompat.getColor(requireContext(), R.color.colorPrimary)))
            toolbar.setTitleTextColor(textView.currentTextColor)
            // Default status bar color is the one from the theme.
            requireActivity().window.statusBarColor = when (textView.id) {
                R.id.lblVibrant, R.id.lblLightVibrant, R.id.lblDarkVibrant ->
                    it.getDarkVibrantColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
                else -> it.getDarkMutedColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
            }
        }
    }

    // Returns background color of the received view or default one.
    private fun getBackgroundColor(view: View, defaultColor: Int): Int =
            (view.background as? ColorDrawable)?.color ?: defaultColor

    companion object {

        fun newInstance() = MainFragment()

    }

}