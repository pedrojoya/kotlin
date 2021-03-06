@file:JvmName("PicassoExt")

package es.iessaladillo.pedrojoya.pr040.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?,
                      @DrawableRes placeholderResId: Int? = null,
                      @DrawableRes errorResId: Int? = null) {
    Picasso.with(context)
            .load(url).run {
                placeholderResId?.let { placeholder(it) }
                errorResId?.let { error(it) }
                into(this@loadUrl)
            }
}
