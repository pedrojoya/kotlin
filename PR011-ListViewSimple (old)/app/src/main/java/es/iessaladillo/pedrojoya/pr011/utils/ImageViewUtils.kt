package es.iessaladillo.pedrojoya.pr011.utils

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.widget.ImageView

fun ImageView.tint(@ColorRes colorRes: Int) {
    val colors = ContextCompat.getColorStateList(this.context, colorRes)
    val drawable = DrawableCompat.wrap(this.drawable)
    DrawableCompat.setTintList(drawable, colors)
    this.setImageDrawable(drawable)
}