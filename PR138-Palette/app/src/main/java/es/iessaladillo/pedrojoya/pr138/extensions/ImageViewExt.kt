@file:JvmName("ImageViewExt")

package es.iessaladillo.pedrojoya.pr138.extensions

import androidx.annotation.DrawableRes
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String,
                      @DrawableRes placeholderResId: Int? = null,
                      @DrawableRes errorResId: Int? = null,
                      onSuccessAction: () -> Unit = {},
                      onErrorAction: () -> Unit = {}) {
    with (Picasso.with(context).load(url)) {
        placeholderResId?.let { placeholder(it) }
        errorResId?.let { error(it) }
        into(this@loadUrl, object : Callback {
            override fun onSuccess() {
                onSuccessAction()
            }

            override fun onError() {
                onErrorAction()
            }
        })
    }
}

