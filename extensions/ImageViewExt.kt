@file:JvmName("ImageViewExt")

package pedrojoya.iessaladillo.es.pr201.extensions

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?,
                      @DrawableRes placeholderResId: Int? = null,
                      @DrawableRes errorResId: Int? = null) {
    with (Picasso.with(context).load(url)) {
        placeholderResId?.let { placeholder(it) }
        errorResId?.let { error(it) }
        into(this@loadUrl)
    }
}

fun ImageView.loadUrl(url: String?,
                      @DrawableRes placeholderResId: Int? = null,
                      @DrawableRes errorResId: Int? = null,
                      onErrorAction: () -> Unit = {},
                      onSuccessAction: () -> Unit = {}) {
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

