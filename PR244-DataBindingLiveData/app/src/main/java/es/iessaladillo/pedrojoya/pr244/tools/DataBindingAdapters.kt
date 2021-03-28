package es.iessaladillo.pedrojoya.pr244.tools

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl", "placeholder", "error"], requireAll = false)
fun ImageView.setImageUrl(url: String?, drawablePlaceholder: Drawable?,
                drawableError: Drawable?) {
    Picasso.with(context).load(url)
            .placeholder(drawablePlaceholder)
            .error(drawableError)
            .into(this)
}

@BindingAdapter("visible")
fun View.bindVisible(visible: Boolean?) {
    visibility = if (visible == true) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("visible")
fun View.bindVisible(visible: LiveData<Boolean>?) {
    visibility = if (visible?.value == true) View.VISIBLE else View.INVISIBLE
}
