@file:JvmName("PicassoExt")
package es.iessaladillo.pedrojoya.pr057.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String, @DrawableRes placeholderResId: Int) {
    Picasso.with(context).load(url).placeholder(placeholderResId).error(
            placeholderResId).into(this)
}
