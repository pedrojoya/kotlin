@file:JvmName("PicassoUtils")

package pedrojoya.iessaladillo.es.pr225.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
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
