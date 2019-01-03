@file:JvmName("FragmentExt")

package es.iessaladillo.pedrojoya.pr178.extensions

import android.os.Parcelable
import androidx.fragment.app.Fragment

fun <T : Parcelable> Fragment.extraParcelable(extraId: String) = lazy {
    arguments?.getParcelable<T>(extraId) ?: throw IllegalArgumentException("Required argument")
}
