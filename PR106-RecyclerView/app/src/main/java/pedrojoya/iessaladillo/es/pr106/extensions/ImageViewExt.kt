package pedrojoya.iessaladillo.es.pr106.extensions

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String,
                      @DrawableRes placeholderResId: Int? = null,
                      @DrawableRes errorResId: Int? = null) {
    with (Picasso.with(context).load(url)) {
        placeholderResId?.let { placeholder(it) }
        errorResId?.let { error(it) }
        into(this@loadUrl)
    }
}
