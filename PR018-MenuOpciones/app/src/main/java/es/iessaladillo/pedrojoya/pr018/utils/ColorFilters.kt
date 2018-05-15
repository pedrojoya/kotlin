@file:JvmName("ColorFilters")
package es.iessaladillo.pedrojoya.pr018.utils

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter

val GREY_COLOR_FILTER = ColorMatrixColorFilter(ColorMatrix().apply {
    setSaturation(0f)
})

// Convert to grayscale, then apply brown color
val SEPIA_COLOR_FILTER = ColorMatrixColorFilter(ColorMatrix().apply {
    setSaturation(0f)
    postConcat(ColorMatrix().apply { setScale(1f, 1f, 0.8f, 1f) })
})

// Convert to grayscale, then scale and clamp
val BINARY_COLOR_FILTER = ColorMatrixColorFilter(ColorMatrix().apply {
    setSaturation(0f)
    val m = 255f
    val t = -255 * 128f
    val threshold = ColorMatrix(
            floatArrayOf(m, 0f, 0f, 1f, t, 0f, m, 0f, 1f, t, 0f, 0f, m, 1f, t, 0f, 0f, 0f, 1f, 0f))
    postConcat(threshold)
})

val INVERTED_COLOR_FILTER = ColorMatrixColorFilter(ColorMatrix(
        floatArrayOf(-1f, 0f, 0f, 0f, 255f, 0f, -1f, 0f, 0f, 255f, 0f, 0f, -1f, 0f, 255f, 0f, 0f,
                0f, 1f, 0f)))
